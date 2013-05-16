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

});