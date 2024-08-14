package com.example.ozonchick_new_version.Screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.data.db.users
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserAuth
import com.example.ozonchick_new_version.Screens.Splash.Splash_Screen
import io.paperdb.Paper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Paper.book().delete("User")
            Navigator(screen = Splash_Screen())
/*            if (users.size == 0) {
                users.add(User("Егор", 0.5, "ваава@mail.ru"))
                Paper.book().write("Users", users)
                Log.d("Sql", users.size.toString())
            }
            else {
                Log.d("Sql", users[0].userName)
            }*/
/*            if (userAuthInformation.size == 0) {
                userAuthInformation.add(UserAuth("gg", "dd"))
                Paper.book().write("User", userAuthInformation)
                Paper.book("User").destroy()
                Log.d("Sql", userAuthInformation.size.toString())
            }
            else {
                Log.d("Sql", userAuthInformation[0].email)
            }*/

//            Paper.book().delete("User")
//            Log.d("Sql", userAuthInformation.size.toString())

        }
    }
}