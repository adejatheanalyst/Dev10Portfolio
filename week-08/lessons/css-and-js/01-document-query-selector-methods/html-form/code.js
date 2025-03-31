let paper = {
  title: "The Benefits of Flipping Your Pillow to the Cold Side",
  authors: "Wnt. Snooze, M. Sleepy",
  abstract: `Flipping your pillow to the cold side is shown have many benefits.
It's nice.
You should try it.`,
  discipline: "philosophy",
  urgency: "8",
  categories: ["daring", "original"],
};

function bindPaper(p) {
  document.getElementById("title").value = p.title;
  document.getElementById("authors").value = p.authors;
  document.getElementById("abstract").value = p.abstract;
  document.getElementById("discipline").value = p.discipline;

  const radioButton = document.querySelector(
    `input[name="urgency"][value="${p.urgency}"]`
  );

  if (radioButton) {
    radioButton.checked = true;
  }

  for (const checkBox of document.querySelectorAll(
    `input[name="categories"]`
  )) {
    checkBox.checked = p.categories.includes(checkBox.value);
  }
}

bindPaper(paper);

document.querySelector("form").addEventListener("submit", handleSubmit);

function handleSubmit(evt) {
  evt.preventDefault();

  let editedPaper = {
    title: document.getElementById("title").value,
    authors: document.getElementById("authors").value,
    abstract: document.getElementById("abstract").value,
    discipline: document.getElementById("discipline").value,
    urgency: document.querySelector('input[name="urgency"]:checked').value,
    categories: [
      ...document.querySelectorAll('input[name="categories"]:checked'),
    ].map((chk) => chk.value),
  };

  console.log(editedPaper);
}
