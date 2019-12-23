/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('output').addEventListener('click', function() {
		Array.from(document.getElementsByClassName('off')).forEach(function(e) {
			e.nextElementSibling.removeAttribute("name");
		});
		document.getElementById('submit').submit();
	});

}, false);
var switchOnOff = function(subSkill) {
	if (subSkill.className == "off") {
		subSkill.classList.remove('off')
		subSkill.classList.add('on')
	} else {
		subSkill.classList.remove('on')
		subSkill.classList.add('off')
	}
}
