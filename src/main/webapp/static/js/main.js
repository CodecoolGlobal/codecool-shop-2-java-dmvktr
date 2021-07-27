import {dataHandler} from "./data-handler.js";

const page = {
    categoryEndpointURL: "/getProductsByCategory",
    baseImagePath: "/static/img/product-img/",

    init: function () {
        this.initCategoryMenuEventListeners();
    },

    initCategoryMenuEventListeners: function () {
        const categories = document.querySelector(".catagories-menu");
        let menuOptions = categories.querySelectorAll('li');

        menuOptions.forEach(o => o.addEventListener('click', () => {
            const URL = `${this.categoryEndpointURL}?id=${o.dataset.categoryId}`;
            dataHandler.fetchData(URL, this.rebuildProducts);
        }))
    },

    rebuildProducts: function (products) {
        // implement function
    }
}

page.init();