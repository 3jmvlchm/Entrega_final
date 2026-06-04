/*!
* Start Bootstrap - Modern Business v5.0.7 (https://startbootstrap.com/template-overviews/modern-business)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-modern-business/blob/master/LICENSE)
*/
(() => {
    const formatter = new Intl.NumberFormat("es-CO", {
        style: "currency",
        currency: "COP",
        maximumFractionDigits: 0
    });

    document.querySelectorAll(".portfolio-quantity").forEach((input) => {
        const updateTotal = () => {
            const price = Number(input.dataset.price || 0);
            const quantity = Math.max(1, Number.parseInt(input.value || "1", 10) || 1);
            const total = document.getElementById(input.dataset.totalTarget);
            input.value = quantity;
            if (total) {
                total.textContent = formatter.format(price * quantity);
            }
        };

        input.addEventListener("input", updateTotal);
        updateTotal();
    });

    document.querySelectorAll(".portfolio-order-form").forEach((form) => {
        form.addEventListener("submit", (event) => {
            const quantity = form.querySelector(".portfolio-quantity");
            if (!quantity || Number(quantity.value) < 1) {
                event.preventDefault();
                alert("Ingresa una cantidad valida para el pedido.");
            }
        });
    });
})();
