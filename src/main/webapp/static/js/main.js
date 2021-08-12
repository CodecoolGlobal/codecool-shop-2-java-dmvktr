import {dataHandler} from "./data-handler.js";

const page = {
    userIdPlaceHolder: document.querySelector("#session-user-id").value,
    updateOrderBaseURL: "/update-order",
    categoryEndpointURL: "/getProductsByCategory",
    supplierEndpointURL: "/getProductsBySuppliers",
    baseImagePath: "/static/img/product-img/",
    currentlyActiveCategory: null,

    init: function (){
        this.initCategoryMenuEventListeners();
        this.initSupplierMenuEventListeners();
        this.initCartEventListeners();
    },

    initCartEventListeners: function () {
        let carts = document.querySelectorAll('.product-cart');

        carts.forEach(cart => cart.addEventListener('click', (e) => {
            e.preventDefault();
            this.startShoppingCartAnimation(cart);
            this.cleanUpAfterAnimation(cart);
            const URL = this.assembleURL(cart);
            dataHandler.fetchData(URL, this.updateCartInMenu);
        }))
    },

    assembleURL(cart){
        let URL;
        if(page.userIdPlaceHolder === ""){
            URL = `${this.updateOrderBaseURL}?user_id=${this.userIdPlaceHolder}&product_id=${cart.dataset.productId}&quantity_diff=1`;
        } else {
            URL = `${this.updateOrderBaseURL}?product_id=${cart.dataset.productId}&quantity_diff=1`;
        }
        return URL;
    },

    updateCartInMenu: function (cartContent) {
        document.querySelector("#sidebar-cart").innerHTML = `(${cartContent['itemCount']})`;
    },

    logResponse: function (data) {
        console.log(data);
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
        for (const category of menuOptions) {
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
                if (this.isCheckBoxTicked(input)) {
                    URL = this.appendActiveCheckBoxIdValueToQueryString(URL, input);
                }
            }
            URL = page.trimEndOfURLString(URL);
            dataHandler.fetchData(URL, this.rebuildProducts);
        }))
    },

    isCheckBoxTicked: function (box){
        return box.checked;
    },

    appendActiveCheckBoxIdValueToQueryString: function (URL, checkBox){
        URL += `${checkBox.id}=x&`;
        return URL;
    },

    trimEndOfURLString: function (URL) {
        return URL.slice(0, -1);
    },

    updateActiveCategory: function (eventTarget){
       page.currentlyActiveCategory.classList.toggle('active');
       eventTarget.classList.toggle('active');
       page.currentlyActiveCategory = eventTarget;
    },

    rebuildProducts: function (products) {
        console.log(products);
        const container = document.querySelector('#product-container');
        container.innerHTML = "";
        if (products == null) {
            page.displayNoProductFoundError(container);
        } else {
                for (const product of products) {
                    page.rebuildSingleProduct(container, product);
                }
                page.initCartEventListeners();
            }
        },

    displayNoProductFoundError: function (container){
        container.insertAdjacentHTML('beforeend',
            `<div class="no-product-error-container">
                    <div class="error-img">
                        <img src="/static/img/error-img/error_no_product.jpg">
                    </div>
                </div>`)
    },

    rebuildSingleProduct: function (container, product){
        console.log(product)
        container.insertAdjacentHTML('beforeend', `
        <div class="col-12 col-sm-4 col-md-12 col-xl-4">
            <div class="single-product-wrapper">
                <div class="product-img">
                    <img src="${this.baseImagePath}${product['imagePath']}" alt="">
                    <img class="hover-img" src="${this.baseImagePath}${product['hoverImagePath']}" alt="">
                </div>
                <div class="product-description d-flex align-items-center justify-content-between">
                    <div class="product-meta-data">
                        <div class="line"></div>
                        <p class="lead product-price">${product['defaultPrice']} ${product['defaultCurrency']}</p>
                        <div class="ratings">
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </div>
                        <a href="">
                            <h4 class="card-title">${product['name']}</h4>
                        </a>
                        <p class="card-text">${product['description']} </p>
                    </div>
                    <div class="ratings-cart text-right" style="min-width: 50px">
                        <div class="cart">
                            <a href="#" data-toggle="tooltip" data-placement="left" title data-original-title="Add to Cart"><img data-product-id=${product['id']} class="product-cart" src="/static/img/core-img/cart-purple.png" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
        )
    },

    startShoppingCartAnimation: function (cart){
        cart.classList.add('activate-cart-animation');
    },

    cleanUpAfterAnimation: function (cart){
        setInterval(()=>{
            cart.classList.remove('activate-cart-animation');
        }, 3000);
    },
}

page.init();