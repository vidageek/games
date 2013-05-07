define(['jquery'],
    function($) {
		describe('Hello world', function() {
		        it('says hello', function() {
		                expect(helloWorld()).toEqual("Hello world!");
		        });
		});
		
		function helloWorld(){
			return "Hello world!";
		}
}
);