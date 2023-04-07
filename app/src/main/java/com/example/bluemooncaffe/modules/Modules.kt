package com.example.bluemooncaffe.modules

import com.example.bluemooncaffe.data.OrderManagement
import com.example.bluemooncaffe.repository.Repository
import com.example.bluemooncaffe.repository.RepositoryImpl
import com.example.bluemooncaffe.viewModels.CartViewModel
import com.example.bluemooncaffe.viewModels.LoginViewModel
import com.example.bluemooncaffe.viewModels.MainViewModel
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*val firestoreModule = module {
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
}*/
val repositoryModule= module{
    single{ RepositoryImpl(orderManagement = get<OrderManagement>()) }
    single <Repository> {RepositoryImpl(get())}
}
val mainModule= module {
    viewModel{
        MainViewModel(drinksRepository = get())
    }
}

val cartModule= module {
    viewModel {
        CartViewModel(drinksRepository = get())
    }
}

val ordersModule = module {
    viewModel {
        OrdersViewModel(drinksRepository = get())
    }
}

val loginModule = module {
    viewModel {
        LoginViewModel(drinksRepository = get())
    }
}
val orderManagementModule= module{
    single <OrderManagement> {
        OrderManagement()
    }
}