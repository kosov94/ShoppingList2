package com.kosov941.shoppinglist2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kosov941.shoppinglist2.domain.ShopItem
import com.kosov941.shoppinglist2.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("Name $i", i.toDouble(), true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateShopListLD(shopList)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateShopListLD(shopList)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateShopListLD(shopList: List<ShopItem>) {
        shopListLD.value = ShopListRepositoryImpl.shopList.toList()
    }
}