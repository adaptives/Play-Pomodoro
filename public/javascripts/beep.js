function beep(soundObj) {
	var sound = document.getElementById(soundObj);
	if(sound != null && typeof sound.Play === 'function') {
		sound.Play();
	} else {
		alert("Task is done !");
	}	
}