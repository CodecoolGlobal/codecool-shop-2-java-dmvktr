import {dataHandler} from "./data-handler.js";


const cart = {
    userId: 1,
    updateOrderBaseURL: "/update-order",

    init: function (){
        this.initCartEventListeners();
        this.displayCartTotalSummary();
    },

    initCartEventListeners: function (){
        let carts = document.querySelectorAll('.product-cart');

        carts.forEach(cart => cart.addEventListener('click', ()=>{
            const URL = `${this.updateOrderBaseURL}?user_id=${this.userId}&product_id=${cart.dataset.productId}&quantity_diff=1`;
            dataHandler.fetchData(URL, this.logResponse);
        }))
    },

    displayCartTotalSummary: function (){
        const subtotalValue = document.querySelector('.subtotal').dataset.subtotal;
        const cartSummarySubtotalSpan = document.querySelector('#cart-summary-subtotal');
        const cartSummaryTotalSpan = document.querySelector('#cart-summary-total');
        cartSummarySubtotalSpan.innerHTML = subtotalValue;
        cartSummaryTotalSpan.innerHTML = subtotalValue;
    },

    logResponse: function (data){
        console.log(data);
    },
}

cart.init();