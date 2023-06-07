const handleChange = (current, allToggles) => {
  const onCount = allToggles.filter(t => t.checked).length;
  const offCount = allToggles.length - onCount;

  if (onCount < 2) {

    const otherOffToggles = allToggles.filter(t => t !== current && !t.checked);
    shuffle(otherOffToggles);

    const n = randomElement([1, 1, 1, 1, 1, 1, 2, 2, 3]);
    otherOffToggles.slice(0, n).forEach(t => {
      t.checked = true;
    });

  } else if (offCount < 2) {

    const otherOnToggles = allToggles.filter(t => t !== current && t.checked);
    shuffle(otherOnToggles);

    const n = randomElement([1, 1, 1, 1, 1, 1, 2, 2, 3]);
    otherOnToggles.slice(0, n).forEach(t => {
      t.checked = false;
    });

  }
}

(() => {
  const container = document.querySelector("#container");
  const allToggles = [];
  const N = 9;

  for (let i = 0; i < N; i++) {
    const toggle = new Toggle("toggle" + i);
    toggle.parent(container);
    allToggles.push(toggle);
    toggle.onChange(() => handleChange(toggle, allToggles));
  }

  randomElement(allToggles).toggle();
})()
