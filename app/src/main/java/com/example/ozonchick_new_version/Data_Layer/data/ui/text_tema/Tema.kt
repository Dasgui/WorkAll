package com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Active_SideText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.MainFontFamily
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.RobotoFamily
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Text_Color_Active
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Text_Color_Orange
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Text_Color_Passive

object Tema {
    val TextField_Litch = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = Text_Color_Passive,
        textAlign = TextAlign.Justify
    )
    val TextField_Black = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = Color.Blue,
        textAlign = TextAlign.Justify,
    )
    val SecondaryText_14_16_500 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        color = Gray_Color_Passive_Secondary
    )

    val MainTextBlue_16 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        color = Button_Color_Blue
    )

    val ActiveSideText_14_16_500 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        color = Text_Color_Active
    )
    val PassiveSideText_14_16_500 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
//        color = Gray_Color_Active_SideText
    )

    val BoldSideText_12_16 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp,
//        color = Text_Color_Passive
    )
    val SecondText_12_16_400 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp,
        color = Text_Color_Active,
        fontFamily = RobotoFamily
    )

    val OrangeSideText_12_16 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp,
        color = Text_Color_Orange
    )


    val BigMainText_24_24_700 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(700),
        color = Primary_Color,
        textAlign = TextAlign.Center,
    )
    val SmallMainText_16_20_400 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(400),
        color = Text_Color_Active,
        textAlign = TextAlign.Center,
    )


    val ButtonText_14_10_700_Passive = TextStyle(
        fontSize = 14.sp,
        lineHeight = 9.38.sp,
        fontWeight = FontWeight(700),
        color = Primary_Color,
    )
    val ButtonText_14_10_700_Active = TextStyle(
        fontSize = 14.sp,
        lineHeight = 9.38.sp,
        fontWeight = FontWeight(700),
        color = Color.White,
    )

    val ButtonText_16_16_700_Neutral = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(700),
        color = Color.White,
    )
/*    val Text_16_16_Medium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium*//*FontWeight(700)*//*,
        fontFamily = RobotoFamily
    )*/
    val MainText_16_30_500 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight.Medium/*FontWeight(500)*/,
        color = Text_Color_Active,
        fontFamily = RobotoFamily
    )

    val SecondaryText_14_16_400 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        color = Gray_Color_Passive_Secondary,
    )

    val MainText_14_16_400 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = RobotoFamily,
        fontWeight = FontWeight.W400,
        color = Text_Color_Active,
    )

    val MainText_24_30_500 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 30.sp,
        fontWeight = FontWeight(500),
        color = Text_Color_Active,
    )

    val SideText_12_16_400 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        lineHeight = 16.sp,
//        color = Gray_Color_Passive_Secondary
    )

    val BottomText_14_14_400 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp,
        fontFamily = RobotoFamily
    )



    val Text_14_14_Regular = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 14.sp,
        fontFamily = MainFontFamily
    )
    val Text_16_16_Medium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        fontFamily = MainFontFamily
    )
    val Text_16_16_Regular = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        fontFamily = MainFontFamily
    )
    val Text_18_18_Regular = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 18.sp,
        fontFamily = MainFontFamily
    )
    val Text_18_18_Medium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp,
        fontFamily = MainFontFamily
    )
    val Text_20_20_Medium = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        fontFamily = MainFontFamily
    )
    val Text_24_24_Medium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        fontFamily = MainFontFamily
    )
    val Text_24_24_Bold = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp,
        fontFamily = MainFontFamily
    )
    val Text_18_18_Bold = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp,
        fontFamily = MainFontFamily
    )
}