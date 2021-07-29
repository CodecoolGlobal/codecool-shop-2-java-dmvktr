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
        const quantityDisplayField = quantityDiv.querySelector('input');
        const quantityDiff = evt.currentTarget.classList.contains('qty-plus') ? 1 : -1;
        const currentQuantityValue = parseInt(quantityDisplayField.value);
        if(currentQuantityValue === 1 && cart.isQuantityDecreaseButtonUsed(evt)){
            return;
        }
        let newQuantity = currentQuantityValue + quantityDiff;
        const productId = quantityDiv.dataset.productId;
        const URL = `${cart.updateOrderBaseURL}?user_id=${cart.userId}&product_id=${productId}&quantity_diff=${quantityDiff}`;

        quantityDisplayField.value = String(newQuantity);
        dataHandler.fetchData(URL, cart.rebuildCartSummary);
    },

    logResponse: function (data) {
        console.log(data);
    },

    isQuantityDecreaseButtonUsed: function (evt){
        return evt.currentTarget.classList.contains('qty-minus');
    },

    buildCartSummaryTableBody: function (lineItem, tbody){
        const quantity = lineItem['quantity'];
        const productName = lineItem['product']['name'];
        const productId = lineItem['product']['id'];
        const productImagePath = `${lineItem['product']['baseImagePath']}${lineItem['product']['imagePath']}`;
        const subTotal = lineItem['subTotalPrice'];
        const unitPrice = `${lineItem['product']['defaultPrice']} ${lineItem['product']['defaultCurrency']}`;

        tbody.insertAdjacentHTML('beforeend',
            `<tr>
                    <td class="cart_product_img">
                        <a href="#"><img src="${productImagePath}" alt="Product"></a>
                    </td>
                    <td class="name">
                        <span>${productName}</span>
                    </td>
                    <td class="price" data-price="${unitPrice}">
                        <span>${unitPrice}</span>
                    </td>
                    <td class="qty">
                        <div class="qty-btn d-flex">
                            <p>Qty</p>
                            <div class="quantity" data-product-id="${productId}">
                                <span class="qty-minus"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="${quantity}">
                                <span class="qty-plus"><i class="fa fa-plus" aria-hidden="true"></i></span>
                            </div>
                        </div>
                    </td>
                    <td class="subtotal" data-subtotal="${subTotal}">
                        <span>${subTotal} ${lineItem['product']['defaultCurrency']}</span>
                    </td>
                </tr>`)
    },

    rebuildCartSummary: function (cartContent){
        if(cartContent != null){
            const tbody = document.querySelector('tbody');
            tbody.innerHTML = "";
            for (const lineItem of cartContent['items']) {
                cart.buildCartSummaryTableBody(lineItem, tbody);
            }
        }
        cart.updateCartTotal(cartContent);
        cart.initQuantityAdjustmentButtonListeners();
    },

    updateCartTotal: function (cartContent){
        const cartSummarySubtotal = document.querySelector("#cart-summary-subtotal");
        const cartSummaryTotal = document.querySelector("#cart-summary-total");

        cartSummarySubtotal.innerText = cartContent['totalPrice'];
        cartSummaryTotal.innerText = cartContent['totalPrice'];
    },
}

cart.init();