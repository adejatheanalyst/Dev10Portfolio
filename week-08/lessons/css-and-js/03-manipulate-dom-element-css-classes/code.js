function handleClick(evt) {
  document.body.classList.remove(
      "red-ish", "blue-ish", "yellow-ish");
  
  document.body.classList.add(evt.target.className);
}
