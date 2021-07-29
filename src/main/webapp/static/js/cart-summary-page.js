import {dataHandler} from "./data-handler.js";


const cart = {
    userId: 1,
    updateOrderBaseURL: "/update-order",
    cartSummarySubtotalSpan: document.querySelector('#cart-summary-subtotal'),
    cartSummaryTotalSpan: document.querySelector('#cart-summary-total'),

    init: function () {
        this.initCartEventListeners();
        this.displayCartTotalSummary();
        this.initQuantityAdjustmentButtonListeners();
    },

    initCartEventListeners: function () {
        let carts = document.querySelectorAll('.product-cart');

        carts.forEach(cart => cart.addEventListener('click', () => {
            const URL = `${this.updateOrderBaseURL}?user_id=${this.userId}&product_id=${cart.dataset.productId}&quantity_diff=1`;
            dataHandler.fetchData(URL, this.logResponse);
        }))
    },

    displayCartTotalSummary: function () {
        const subtotalValue = document.querySelector('.subtotal').dataset.subtotal;
        cart.setCartSummaryField(cart.cartSummarySubtotalSpan, subtotalValue);
        cart.setCartSummaryField(cart.cartSummaryTotalSpan, subtotalValue);
    },

    setCartSummaryField: function (fieldName, value) {
        fieldName.innerHTML = value;
    },

    initQuantityAdjustmentButtonListeners: function () {
        const increaseQuantityButtons = document.querySelectorAll('.qty-plus');
        const decreaseQuantityButtons = document.querySelectorAll('.qty-minus');
        increaseQuantityButtons.forEach(button => button.addEventListener('click', this.adjustCartContent))
        decreaseQuantityButtons.forEach(button => button.addEventListener('click', this.adjustCartContent))
    },

    adjustCartContent: function (evt) {
        const quantityDiv = evt.target.closest('.quantity');
        const quantityDiff = evt.currentTarget.classList.contains('qty-plus') ? "1" : "-1";
        const productId = quantityDiv.dataset.productId;
        const URL = `${cart.updateOrderBaseURL}?user_id=${cart.userId}&product_id=${productId}&quantity_diff=${quantityDiff}`;
        dataHandler.fetchData(URL, cart.logResponse);
    },

    logResponse: function (data) {
        console.log(data);
    },
}

cart.init();