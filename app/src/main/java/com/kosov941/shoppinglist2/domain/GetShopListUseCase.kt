package com.kosov941.shoppinglist2.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList():List<ShopItem>{
        return shopListRepository.getShopList()
    }
}