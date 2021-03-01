# **Android for SDK**

*Android app to simulate a stopwatch and send/retrieve date-time logs using the open API [JsonBin](https://jsonbin.io//).*

## Wiremocks

`Splash`, `List` and `Details` view.

<p align="center">
<img src="images/wiremock_splash.jpg" width="32%">
<img src="images/wiremock_list.jpg" width="32%">
<img src="images/wiremock_details.jpg" width="32%">
</p>


## Architecture

We will be using the **Architecture Components** approach of the [Android Jetpack](https://developer.android.com/jetpack/) libraries. This means an extensive use of the **MVVM** pattern and **reactive programming**. The controller interfaces will be implemented in `ViewModel` classes, views will be represented for their respective `Fragments` and models will be wrapped inside `LiveData` objects. LiveData and ViewModels classes are **lifecycle-aware** and they allow the use of **Observer** pattern to handle the app state.  As a plus, these LiveData objects are **thread-safe** and we can exploit this capacity to create **asynchronous requests** to the API to improve performance. With this architecture, our code gains clearness and separation of concerns (with the help of `dependency inversion`) so this facilitates the usage of **TDD**.

In order that we mantain the app smooth and because the timer counter its a CPU-bound task, we need to use **parallelism** in our solution. To achieve that, we will implement a **Looper-Handler** approach. 

For data `persistence` we are going to use `Shared Preferences` due to its simplicity and because is a requirement. 

To get an effective decoupling of the views, the **Data Binding** and **View Binding** libraries will be helpful. Therefore, the views will be `single responsibility` compliance and lightweight in the resources usage.

In the UI, we will be using **Material Desing** components mostly. For the list of logs we select the RecyclerView as is more effective than ListView or similars.

## Tasks

- [X] Define the schema for API endpoints.
- [ ] Setting main views/navigation/layouts.
- [ ] Setting main models/controllers/interfaces.
- [ ] Setup the stopwatch behaviour
- [ ] Setup API client.
- [ ] Request the list of characters.
- [ ] Request character details.
- [ ] Parsing response and display the list of character with RecyclerView.
- [ ] Parsing response and display character details.
- [ ] Adding coroutines to create async requests.
- [ ] Adding persistence.
- [ ] Adding tests. **

** Tests will be created upfront using TDD for the **business logic** as much as possible.

## *Extras*

- [X] Splash screen with **Motion Layout**.
- [ ] Dependency Injection with **Hilt**.
- [ ] Animations for fragment transitions and UI components.
- [ ] Day/Night mode.
- [ ] Predominant color detector for Details view using **Palette** component
