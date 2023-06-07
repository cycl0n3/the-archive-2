const randomInt = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

const randomElement = (array) => {
    const index = Math.floor(Math.random() * array.length);
    return array[index];
}

const shuffle = (array) => {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}
