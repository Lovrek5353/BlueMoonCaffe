package com.example.bluemooncaffe

import android.app.Application
import com.example.bluemooncaffe.modules.*
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BlueMoonCaffeApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        //FirebaseApp.initializeApp(this );
        startKoin {
            FirebaseFirestore.getInstance()
            androidLogger()
            androidContext(this@BlueMoonCaffeApplication)
            modules(
                //firestoreModule,
                repositoryModule,
                mainModule,
                cartModule,
                ordersModule,
                loginModule,
                orderManagementModule
            )
        }
    }
}