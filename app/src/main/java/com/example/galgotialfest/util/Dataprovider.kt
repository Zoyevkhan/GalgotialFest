package com.example.galgotialfest.util

import com.example.galgotialfest.R
import com.example.galgotialfest.enums.DashboardMenusCat
import com.example.galgotialfest.enums.GameType
import com.example.galgotialfest.enums.SubCategory
import com.example.galgotialfest.model.CommonModel
import com.example.galgotialfest.model.DashboardMenus
import javax.annotation.meta.When

object Dataprovider {


    var parantCategoryMap:MutableMap<GameType,String> = mutableMapOf()
    var subCategoryMap:MutableMap<SubCategory,String> = mutableMapOf()

    fun setParentCatMap(){
        parantCategoryMap.put(GameType.INDOOR,"indoor")
        parantCategoryMap.put(GameType.OUTDOOR,"outdoor")
        parantCategoryMap.put(GameType.FUNGAME,"fungame")
    }
    fun setSubCatMap(){
        subCategoryMap.put(SubCategory.QUIZ,"quiz")
        subCategoryMap.put(SubCategory.DEBATE,"debate")
        subCategoryMap.put(SubCategory.BGMI,"bgmi")
        subCategoryMap.put(SubCategory.CHESS,"chess")
        subCategoryMap.put(SubCategory.RANGOLI,"rangoli")
        subCategoryMap.put(SubCategory.POSTER_MAKING,"postermaking")
        subCategoryMap.put(SubCategory.DANCE,"dance")
        subCategoryMap.put(SubCategory.RAMP_WALK,"rampwalk")
        subCategoryMap.put(SubCategory.SINGING,"singing")
        subCategoryMap.put(SubCategory.TREASURE_HUNT,"treasurehunt")
        subCategoryMap.put(SubCategory.NUKKAD_NATAK,"nukkadnatak")
        subCategoryMap.put(SubCategory.MUSICAL_CHAIR,"musicalchair")
        subCategoryMap.put(SubCategory.LEMON_SPOON,"lemonspoon")
        subCategoryMap.put(SubCategory.TUG_OF_WAR,"tugofwar")
        subCategoryMap.put(SubCategory.PUSH_UP,"pushup")
        subCategoryMap.put(SubCategory.FROG_JUMP,"frogjump")
    }

    fun getMenusfromDB():List<CommonModel>{
        var list= mutableListOf<CommonModel>()
        list.add(CommonModel("Indoor", R.drawable.t_menu,DashboardMenusCat.INDOOR.ordinal))
        list.add(CommonModel("Outdoor", R.drawable.t_menu,DashboardMenusCat.OUTDOOR.ordinal))
        list.add(CommonModel("Fun Games", R.drawable.t_menu,DashboardMenusCat.FUNGAME.ordinal))
        list.add(CommonModel("Teacher", R.drawable.t_menu,DashboardMenusCat.TEACHER.ordinal))
        list.add(CommonModel("Student", R.drawable.t_menu,DashboardMenusCat.STUDENT.ordinal))
        list.add(CommonModel("Logout", R.drawable.t_menu,DashboardMenusCat.LOGOUT.ordinal))
        return list
    }
    fun getMenuDataByCategory(menu:CommonModel):List<CommonModel>{
        var list= when(menu.category){
            DashboardMenusCat.INDOOR.ordinal-> getIndoorList()
            DashboardMenusCat.OUTDOOR.ordinal-> getOutdoorList()
            DashboardMenusCat.FUNGAME.ordinal-> getFunGameList()
            else-> listOf()
        }
        return list
    }

    private fun getFunGameList(): List<CommonModel> {
        var indoorList= mutableListOf<CommonModel>()
    indoorList.add(CommonModel("Musical Chair", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.MUSICAL_CHAIR))
        indoorList.add(CommonModel("Lemon Spoon", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.LEMON_SPOON))
        indoorList.add(CommonModel("Tug Of War", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.TUG_OF_WAR))
        indoorList.add(CommonModel("Push Up", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.PUSH_UP))
        indoorList.add(CommonModel("Frog Jump", R.drawable.nukkad_natak,DashboardMenusCat.FUNGAME.ordinal,GameType.FUNGAME,SubCategory.FROG_JUMP))
        return indoorList

    }

    fun getIndoorList():List<CommonModel>{
        var indoorList= mutableListOf<CommonModel>()
        indoorList.add(CommonModel("Debate", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.DEBATE))
        indoorList.add(CommonModel("Quiz (Play Card)", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.QUIZ))
        indoorList.add(CommonModel("BGMI", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.BGMI))
        indoorList.add(CommonModel("Chess", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.CHESS))
        indoorList.add(CommonModel("Rangoli", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.RANGOLI))
        indoorList.add(CommonModel("Paster Making", R.drawable.nukkad_natak,DashboardMenusCat.INDOOR.ordinal,GameType.INDOOR,SubCategory.POSTER_MAKING))
        return indoorList
    }
    fun getOutdoorList():List<CommonModel>{
        var indoorList= mutableListOf<CommonModel>()
        indoorList.add(CommonModel("Dance", R.drawable.nukkad_natak,DashboardMenusCat.OUTDOOR.ordinal,GameType.OUTDOOR,SubCategory.DANCE))
        indoorList.add(CommonModel("Ramp Walk", R.drawable.nukkad_natak,DashboardMenusCat.OUTDOOR.ordinal,GameType.OUTDOOR,SubCategory.RAMP_WALK))
        indoorList.add(CommonModel("Singing", R.drawable.nukkad_natak,DashboardMenusCat.OUTDOOR.ordinal,GameType.OUTDOOR,SubCategory.SINGING))
        indoorList.add(CommonModel("Treasure Hunt", R.drawable.nukkad_natak,DashboardMenusCat.OUTDOOR.ordinal,GameType.OUTDOOR,SubCategory.TREASURE_HUNT))
        indoorList.add(CommonModel("Nukkad Natak", R.drawable.nukkad_natak,DashboardMenusCat.OUTDOOR.ordinal,GameType.OUTDOOR,SubCategory.NUKKAD_NATAK))
        return indoorList
    }


}