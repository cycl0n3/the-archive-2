class Toggle {
    constructor(id) {
        const wrapper = document.createElement("div");
        wrapper.classList.add("toggle");
    
        const input = document.createElement("input");
        input.type = "checkbox"
        input.id = id;
    
        const label = document.createElement("label");
        label.htmlFor = id;
    
        wrapper.appendChild(input);
        wrapper.appendChild(label);
    
        this.wrapper = wrapper;
        this.input = input;
    }

    parent(element) {
        element.appendChild(this.wrapper);
    }

    onChange(callback) {
        this.input.addEventListener('change', callback);
    }

    toggle() {
        this.input.toggleAttribute('checked');
    }

    get checked() {
        return this.input.checked;
    }

    set checked(value) {
        this.input.checked = value;
    }
}
