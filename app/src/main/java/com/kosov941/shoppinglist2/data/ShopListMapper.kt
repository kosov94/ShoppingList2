package com.kosov941.shoppinglist2.data

import com.kosov941.shoppinglist2.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDBModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(shopItemDBModel: ShopItemDBModel) = ShopItem(
        id = shopItemDBModel.id,
        name = shopItemDBModel.name,
        count = shopItemDBModel.count,
        enabled = shopItemDBModel.enabled
    )

    fun mapListDbToListEntity(list: List<ShopItemDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}