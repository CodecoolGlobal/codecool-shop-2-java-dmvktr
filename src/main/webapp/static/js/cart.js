import {dataHandler} from "./data-handler.js";


const cart = {
    userId: 1,
    updateOrderBaseURL: "/update-order",

    init: function (){
        this.initCartEventListeners();
    },

    initCartEventListeners: function (){
        let carts = document.querySelectorAll('.product-cart');

        carts.forEach(cart => cart.addEventListener('click', ()=>{
            const URL = `${this.updateOrderBaseURL}?user_id=${this.userId}&product_id=${cart.dataset.productId}&quantity_diff=1`;
            dataHandler.fetchData(URL, this.testing);
        }))
    },

    testing: function (data){
        console.log(data);
    },
}

cart.init();