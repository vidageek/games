describe('Dom verifier', function() {
	it('checks the output', function() {
		expect(callVerify("<p>Hello world!</p>","<p>Hello world!</p>")).toEqual([]);
	});

	it('checks error', function() {
		expect(callVerify("<p>Hello world!</p>","<p>Hellooooooo world!</p>")).not.toEqual([]);
	});
	
	it('checks the line breaks in answers', function() {

		text = "<table border='1'><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>";
		spacedText = "<table border='1'>\n<tr>\n<td>1</td>\n<td>2</td>\n</tr>\n<tr>\n<td>3</td>\n<td>4</td>\n</tr>\n</table>\n";
		
		expect(callVerify(text, spacedText)).toEqual([]);
	});

	it('parameters in tags', function(){
		expect(callVerify("<a href='asdf'>link</a>","<a href='asdf'>link</a>")).toEqual([]);
	});
	it('comparing image tags', function(){
		expect(callVerify("<img alt='asdf' src='link'/>","<img alt='asdf' src='link'/>")).toEqual([]);
	});
	
	it('error in tags', function(){
		expect(callVerify("<a href='link1'>link</a>","<a href='link2'>link</a>")).not.toEqual([]);
	});
	it('Doctype', function(){
		expect(callVerify("<!DOCTYPE html><html><head><title></title></head><body></body></html>","<!DOCTYPE html><html><head><title></title></head><body></body></html>")).toEqual([]);
	});
	it('comparing image tags with error', function(){
		expect(callVerify("<img alt='asdf' src='linkx'/>","<img alt='asdf' src='link'/>")).not.toEqual([]);
	});
	it('tag without parammeter', function(){
		expect(callVerify("<a href='www.google.com'>Google</a>","<a>Google</a>")).not.toEqual([]);
	});
	
});