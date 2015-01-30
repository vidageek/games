/*
 * Módulo node https://github.com/Olegas/dom-compare
 * transformado em script para browser usando 
 *      gluejs --include lib --include index.js --global domDiff --main index.js --out diff.js
 * Também foi necessário mudar a linha que define o libprefix para 
 *      var libPrefix =  './lib';
 * As mensagens de diff também foram traduzidas para português.
 * */
(function(){
var r=function(){var e="function"==typeof require&&require,r=function(i,o,u){o||(o=0);var n=r.resolve(i,o),t=r.m[o][n];if(!t&&e){if(t=e(n))return t}else if(t&&t.c&&(o=t.c,n=t.m,t=r.m[o][t.m],!t))throw new Error('failed to require "'+n+'" from '+o);if(!t)throw new Error('failed to require "'+i+'" from '+u);return t.exports||(t.exports={},t.call(t.exports,t,t.exports,r.relative(n,o))),t.exports};return r.resolve=function(e,n){var i=e,t=e+".js",o=e+"/index.js";return r.m[n][t]&&t?t:r.m[n][o]&&o?o:i},r.relative=function(e,t){return function(n){if("."!=n.charAt(0))return r(n,t,e);var o=e.split("/"),f=n.split("/");o.pop();for(var i=0;i<f.length;i++){var u=f[i];".."==u?o.pop():"."!=u&&o.push(u)}return r(o.join("/"),t,e)}},r}();r.m = [];
r.m[0] = {
"index.js": function(module, exports, require){
(function(){

   "use strict";

   var libPrefix =  './lib';

   module.exports = {
      compare: require(libPrefix + '/compare'),
      XMLSerializer: require(libPrefix + '/canonizer'),
      revXPath: require(libPrefix + '/revxpath'),
      GroupingReporter: require(libPrefix + '/reporters/groupingReporter.js')
   };

})();
},
"lib/compare.js": function(module, exports, require){
(function(){

   "use strict";

   var type = require('./node_types');
   var Collector = require('./collector');

   function Comparator(options, collector) {
      this._options = options || {};
      if(!collector)
         throw new Error("Collector instance must be specified");
      this._collector = collector;
   }

   Comparator.prototype._filterNodes = function(list) {
      var ret = [],
         i, l, item;
      for (i = 0, l = list.length; i < l; i++) {
         item = list.item(i);
         if (item.nodeType == type.COMMENT_NODE && !this._options.compareComments)
            continue;
         if (item.nodeType == type.TEXT_NODE && ("" + item.nodeValue).trim() == "")
            continue;
         ret.push(item);
      }
      return ret;
   };

   Comparator.prototype._compareNodeList = function(left, right) {
      var lLeft = this._filterNodes(left),
         lRight = this._filterNodes(right),
         i, l;

      for (i = 0, l = Math.max(lLeft.length, lRight.length); i < l; i++) {
         if(lLeft[i] && lRight[i]) {
            if (!this.compareNode(lLeft[i], lRight[i]))
               return false;
         }
         else {
            return this._collector.collectFailure(lLeft[i], lRight[i]);
         }
      }
      return true;
   };

   Comparator.prototype._compareAttributes = function(expected, actual) {
      var aExpected = {}, aActual = {},
         i, l;

      if (!expected && !actual)
         return true;



      for(i = 0, l = expected.length; i < l; i++) {
         aExpected[expected[i].nodeName] = expected[i];
      }

      for(i = 0, l = actual.length; i < l; i++) {
         aActual[actual[i].nodeName] = actual[i];
      }

      for(i in aExpected) {
         // both nodes has an attribute
         if(aExpected.hasOwnProperty(i) && aActual.hasOwnProperty(i)) {
            // but values is differ
            var vExpected = aExpected[i].nodeValue;
            var vActual = aActual[i].nodeValue;
            if(this._options.stripSpaces && aExpected[i].nodeType != type.CDATA_SECTION_NODE) {
               vExpected = vExpected.trim();
               vActual = vActual.trim();
            }
            if(vExpected !== vActual) {
               if(!this._collector.collectFailure(aExpected[i], aActual[i]))
                  return false;
            }
            // remove to check for extra/missed attributes;
            delete aActual[i];
            delete aExpected[i];
         }
      }

      // report all missed attributes
      for(i in aExpected) {
         if(aExpected.hasOwnProperty(i))
            if(!this._collector.collectFailure(aExpected[i], null))
               return false;
      }

      // report all extra attributes
      for(i in aActual) {
         if(aActual.hasOwnProperty(i))
            if(!this._collector.collectFailure(null, aActual[i]))
               return false;
      }

      return true;
   };

   Comparator.prototype.compareNode = function(left, right) {
      var vLeft, vRight, r;

      if (left.nodeName === right.nodeName && left.nodeType == right.nodeType) {
         switch (left.nodeType) {
            case type.DOCUMENT_NODE:
               return this.compareNode(left.documentElement, right.documentElement);
            case type.ELEMENT_NODE:
               return this._compareAttributes(left.attributes, right.attributes) &&
                     this._compareNodeList(left.childNodes, right.childNodes);
            case type.TEXT_NODE:
               // fallthrough
            case type.CDATA_SECTION_NODE:
               // fallthrough
            case type.COMMENT_NODE:
               if (left.nodeType == type.COMMENT_NODE && !this._options.compareComments)
                  return true;
               vLeft = "" + left.nodeValue;
               vRight = "" + right.nodeValue;
               if (this._options.stripSpaces && left.nodeType !== type.CDATA_SECTION_NODE) {
                  vLeft = vLeft.trim();
                  vRight = vRight.trim();
               }
               r = vLeft === vRight;
               return !r ? this._collector.collectFailure(left, right) : r;
            default:
               //throw Error("Node type " + left.nodeType + " comparison is not implemented");
         }
      } else
         return this._collector.collectFailure(left, right);
   };

   module.exports = function(a, b, options) {


      var collector = new Collector(options);
      var comparator = new Comparator(options, collector);
      comparator.compareNode(a, b);

      return collector;

   };

})();
},
"lib/revxpath.js": function(module, exports, require){
(function(){

   "use strict";

   var type = require('./node_types');

   function _describeNode(node) {
      var parent = node.parentNode,
          myName = node.nodeName,
          sameSiblings = parent && parent.getElementsByTagName(myName),
          i, l;

      if(node.nodeType == type.DOCUMENT_NODE)
         return "";

      if(sameSiblings && sameSiblings.length > 1) {
         if(node.hasAttribute('id'))
            return myName + "[@id='" + node.getAttribute('id') + "']";
         for(i = 0, l = sameSiblings.length; i < l; i++) {
            if(sameSiblings.item(i) == node)
               return myName + "[" + (i + 1) + "]";
         }
         throw new Error("Node is not found, but should be!");
      } else
         return myName;
   }

   function _processNode(node, res) {

      res.unshift(_describeNode(node));
      if(node.parentNode)
         _processNode(node.parentNode, res);

   }

   module.exports = function revXPath(node) {

      var res;

      if(!node)
         return "";

      if(node.nodeType == type.DOCUMENT_NODE)
         return "/";

      _processNode(node, res = []);
      return res.join('/');

   };


})();
},
"lib/canonizer.js": function(module, exports, require){
(function(){

   "use strict";

   var c = require('./node_types'), spaces = '  ';


   function _sortAttributes(a, b) {
      return a.nodeName < b.nodeName ? -1 : 1;
   }

   function _canonizeNode(node, indent) {
      var i = new Array(indent + 1).join(spaces), hasChildren = node.childNodes.length > 0, ret;
      ret = i + "<" + node.nodeName +
                     _canonizeAttributes(node.attributes, indent + 1);
      if(hasChildren) {
         ret += ">" + _canonizeNodeList(node.childNodes, indent + 2) +
            "\n" + i + "</" + node.nodeName + ">";
      } else
         ret += " />";
      return ret;
   }

   function _canonizeAttributes(attrs, indent) {

      var aList = [], ret = "", i = new Array(indent + 1);
      if (attrs && attrs.length) {

         Object.keys(attrs).map(function (i) {
            return parseInt(i, 10);
         }).filter(function (i) {
            return typeof(i) == 'number' && i >= 0;
         }).sort(function (a, b) {
            return a < b ? -1 : 1;
         }).forEach(function (k) {
            aList.push(attrs[k]);
         });
         aList.sort(_sortAttributes);
         aList.forEach(function(a){
            ret += "\n" + i.join(spaces) + a.nodeName + "=\"" + (a.nodeValue + "").replace(/"/g, '&quot;') + "\"";
         });
      }
      return ret;
   }

   function _canonizeNodeList(list, indent) {
      var ret = '',
          i, l;
      if(list){
         l = list.length;
         for(i=0; i < l; i++) {
            ret += "\n" + canonize(list.item(i), indent);
         }
      }
      return ret;
   }

   function _canonizeText(nodeType, text, indent) {
      var ret = [], i = new Array(indent + 1).join(spaces);
      switch (nodeType) {
         case c.CDATA_SECTION_NODE:
            ret[0] = "<![CDATA[";
            ret[2] = "]]>";
            break;
         case c.COMMENT_NODE:
            ret[0] = "<!--";
            ret[2] = "-->";
            break;
         case c.TEXT_NODE:
            break;
      }
      if(nodeType !== c.CDATA_SECTION_NODE)
         text = text.trim();
      ret[1] = text;
      return i + ret.join('');
   }

   function canonize(node, indent) {
      indent = indent || 0;
      switch (node.nodeType) {
         case c.DOCUMENT_NODE:
            return canonize(node.documentElement, indent);
         case c.ELEMENT_NODE:
            return _canonizeNode(node, indent);
         case c.CDATA_SECTION_NODE:
            // fallthrough
         case c.COMMENT_NODE:
            // fallthrough
         case c.TEXT_NODE:
            return _canonizeText(node.nodeType, node.nodeValue, indent);
         default:
            throw Error("Node type " + node.nodeType + " serialization is not implemented");
      }

   }

   function XMLSerializer() {}

   XMLSerializer.prototype.serializeToString = function(doc) {
      return canonize(doc);
   };

   module.exports = XMLSerializer;

})();
},
"lib/collector.js": function(module, exports, require){
(function(){

   "use strict";

   var type = require('./node_types');
   var revxpath = require('./revxpath.js');

   var typeMap = {},
       comparatorTypeMap = {};

   typeMap[type.ATTRIBUTE_NODE] = "atributo";
   typeMap[type.ELEMENT_NODE] = "elemento";
   typeMap[type.TEXT_NODE] = "texto";
   typeMap[type.COMMENT_NODE] = "comentário";
   typeMap[type.CDATA_SECTION_NODE] = "CDATA";
   typeMap[type.DOCUMENT_NODE] = "documento";

   Object.keys(type).forEach(function(k){
      comparatorTypeMap[type[k]] = k;
   });

   function nameOrText(node) {
     if (node.nodeType == type.TEXT_NODE) {
        return '"' + node.nodeValue + '"';
     }
     return node.nodeName.toLowerCase();
   }

   function Collector(options) {
      this._diff = [];
      this._options = options || {};
   }

   Collector.prototype._describeNode = function(node) {
      if(node.nodeType == type.TEXT_NODE ||
         node.nodeType == type.CDATA_SECTION_NODE ||
         node.nodeType == type.COMMENT_NODE) {
         return "'" + (this._options.stripSpaces ? node.nodeValue.trim() : node.nodeValue) + "'";
      }
      else
         return "'" + node.nodeName + "'";
   };

   Collector.prototype.getDifferences = function() {
      return this._diff;
   };

   Collector.prototype.getResult = function() {
      return this._diff.length == 0
   };

   Collector.prototype.collectFailure = function(expected, actual) {

      var msg, canContinue = true, vExpected, vActual, ref = expected || actual, cmprtr, r;

      if(this._options.comparators && (cmprtr = this._options.comparators[comparatorTypeMap[ref.nodeType]])) {
         if(!(cmprtr instanceof Array))
            cmprtr = [ cmprtr ];
         for(var i = 0, l = cmprtr.length; i < l; i++) {
            r = cmprtr[i](expected, actual);
            if(r) {
               // true -> ignore differences. Stop immediately, continue;
               if(r === true) {
                  return true;
               }
               // string - treat as error message, continue;
               else if(typeof r == 'string') {
                  msg = r;
                  canContinue = true;
               }
               // object - .message = error message, .stop - stop flag
               else if(typeof r == 'object') {
                  msg = r.message;
                  canContinue = !(!!r.stop);
               }
               break;
            }

         }
      }

      if(!msg) {

         if(expected && !actual) {
            msg = typeMap[expected.nodeType].charAt(0).toUpperCase() + typeMap[expected.nodeType].substr(1) +
               " " + this._describeNode(expected).toLowerCase() + " não foi encontrado";
            canContinue = true;
         }
         else if(!expected && actual) {
            msg = "Extra " + typeMap[actual.nodeType] + " " + this._describeNode(actual);
            canContinue = true;
         }
         else {
            if(expected.nodeType == actual.nodeType) {
               if(expected.nodeName == actual.nodeName) {
                  vExpected = expected.nodeValue;
                  vActual = actual.nodeValue;
                  if(this._options.stripSpaces && expected.nodeType != type.CDATA_SECTION_NODE) {
                     vExpected = vExpected.trim();
                     vActual = vActual.trim();
                  }
                  if(vExpected == vActual)
                     throw new Error("Nodes are considered equal but shouldn't");
                  else {
                     switch(expected.nodeType) {
                        case type.ATTRIBUTE_NODE:
                           msg = "Attributo '" + expected.nodeName + "': Valor deveria ser '" + vExpected + "' mas foi encontrado '" + vActual + "'";
                           break;
                        case type.COMMENT_NODE:
                           msg = "Valor do comentário deveria ser '" + vExpected + "' mas foi encontrado '" + vActual + "'";
                           break;
                        case type.CDATA_SECTION_NODE:
                           msg = "Valor do CDATA deveria ser '" + vExpected + "' mas foi encontrado '" + vActual + "'";
                           break;
                        case type.TEXT_NODE:
                           msg = "Texto deveria ser '" + vExpected + "' mas foi encontrado '" + vActual + "'";
                           break;
                        default:
                           throw new Error("nodeValue is not equal, but nodeType is unexpected");
                     }
                     canContinue = true;
                  }
               }
               else {
                  msg = "Esperava encontrar " + typeMap[expected.nodeType] +
                     " '" + nameOrText(expected) + "' mas foi encontrado '" + nameOrText(actual) + "'";
                  canContinue = false;
               }
            }
            else {
               msg = "Esperava encontrar " + typeMap[expected.nodeType] + " " + nameOrText(expected) + 
                  " mas foi encontrado " + typeMap[actual.nodeType] + " " + nameOrText(actual);
               canContinue = false;
            }
         }
      }

      this._diff.push({
         node: revxpath(ref.ownerElement || ref.parentNode),
         message: msg
      });

      return canContinue;
   };

   module.exports = Collector;


})();
},
"lib/node_types.js": function(module, exports, require){
(function(){

   "use strict";

   module.exports = {
      ELEMENT_NODE: 1,
      ATTRIBUTE_NODE: 2,
      TEXT_NODE: 3,
      CDATA_SECTION_NODE: 4,
      ENTITY_REFERENCE_NODE: 5,
      ENTITY_NODE: 6,
      PROCESSING_INSTRUCTION_NODE: 7,
      COMMENT_NODE: 8,
      DOCUMENT_NODE: 9,
      DOCUMENT_TYPE_NODE: 10,
      DOCUMENT_FRAGMENT_NODE: 11,
      NOTATION_NODE: 12
   };

})();
},
"lib/reporters/groupingReporter.js": function(module, exports, require){
(function() {

   "use strict";

   module.exports = {
      report: function(res) {
         var _res = this.getDefferences(res);
         return Object.keys(_res).map(function(path){
            return [path, "\n\t", _res[path].join('\n\t')].join('');
         }.bind(this)).join('\n');
      },
      getDefferences: function(res) {
         var _res = {};
         res.getDifferences().forEach(function(f){
            (_res[f.node] = (_res[f.node] || [])).push(f.message);
         }.bind(this));
         return _res;
      }
   };

})();
}
};
domDiff = r("index.js");}());
