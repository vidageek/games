function callVerify(reference,challenge)
{
	return verify(stringToHtmlObject(reference),stringToHtmlObject(challenge),challenge);
}
function stringToHtmlObject(s)
{
	return $(s)[0];
}