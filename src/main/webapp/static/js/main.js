import {dataHandler} from "./data-handler.js";

const page = {
    categoryEndpointURL: "/getProductsByCategory",
    supplierEndpointURL: "/getProductsBySuppliers",
    baseImagePath: "/static/img/product-img/",
    currentlyActiveCategory: null,

    init: function (){
        this.initCategoryMenuEventListeners();
        this.initSupplierMenuEventListeners();
    },

    initCategoryMenuEventListeners: function (){
        const categories = document.querySelector(".catagories-menu");
        let menuOptions = categories.querySelectorAll('li');
        this.assignActiveCategory(menuOptions);

        menuOptions.forEach(evt => evt.addEventListener('click', ()=>{
            const URL = `${this.categoryEndpointURL}?id=${evt.dataset.categoryId}`;
            dataHandler.fetchData(URL, this.rebuildProducts);
            this.updateActiveCategory(evt);
        }))
    },

    assignActiveCategory: function (menuOptions) {
        for (let category of menuOptions) {
            if (category.classList.contains('active')) {
                page.currentlyActiveCategory = category;
            }
        }
    },

    initSupplierMenuEventListeners: function (){
        const supplierCheckbox = document.querySelector("#supplier-checkbox");
        let supplierInputs = supplierCheckbox.querySelectorAll('input');

        supplierInputs.forEach(o => o.addEventListener('click', ()=>{
            let URL = `${this.supplierEndpointURL}?`;
            for (const input of supplierInputs) {
                if (input.checked) {
                    URL += `${input.id}=x&`;
                }
            }
            URL = URL.slice(0, -1);
            dataHandler.fetchData(URL, this.rebuildProducts);
        }))
    },

    updateActiveCategory: function (eventTarget){
       page.currentlyActiveCategory.classList.toggle('active');
       eventTarget.classList.toggle('active');
       page.currentlyActiveCategory = eventTarget;
    },

    rebuildProducts: function (products){
        const container = document.querySelector('#product-container');
        container.innerHTML = "";

        for (const product of products) {
            console.log(product);
            page.rebuildSingleProduct(container, product);
        }
    },

    rebuildSingleProduct: function (container, product){
        container.insertAdjacentHTML('beforeend', `
        <div class="col-12 col-sm-6 col-md-12 col-xl-6">
            <div class="single-product-wrapper">
                <div class="product-img">
                    <img src="${this.baseImagePath}${product['imagePath']}" alt="">
                    <img class="hover-img" src="${this.baseImagePath}${product['hoverImagePath']}" alt="">
                </div>
                <div class="product-description d-flex align-items-center justify-content-between">
                    <div class="product-meta-data">
                        <div class="line"></div>
                        <p class="lead product-price">${product['defaultPrice']}</p>
                        <div class="ratings">
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </div>
                        <a href="product-details.html">
                            <h4 class="card-title">${product['name']}</h4>
                        </a>
                        <p class="card-text">${product['description']} </p>
                    </div>
                    <div class="ratings-cart text-right" style="min-width: 50px">
                        <div class="cart">
                            <a href="cart.html" data-toggle="tooltip" data-placement="left" title="Add to Cart"><img src="/static/img/core-img/cart-purple.png" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
        )
    }
}

page.init();