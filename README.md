# **StopWatch**

*Android app to simulate a **StopWatch** and that also saves records into an API using [JsonBin](https://jsonbin.io//).*

<p align="center">
<img src="images/StopWatch_Wiremocks.jpg" width="100%">
</p>

## Architecture

We will be using the **Architecture Components** approach of the [Android Jetpack](https://developer.android.com/jetpack/) libraries. This means an extensive use of the **MVVM** pattern and **reactive programming**. The controller interfaces will be implemented in `ViewModel` classes, views will be represented for their respective `Fragments` and models will be wrapped inside `LiveData` objects. LiveData and ViewModels classes are **lifecycle-aware** and they allow the use of **Observer** pattern to handle the app state.  As a plus, these LiveData objects are **thread-safe** and we can exploit this capacity to create **asynchronous requests** to the API to improve performance. With this architecture, our code gains clearness and separation of concerns (with the help of `dependency inversion`) so this facilitates the usage of **TDD**.

In order that we mantain the app smooth and because the timer counter its a CPU-bound task, we need to use **parallelism** in our solution. To achieve that, we will implement a **Looper-Handler** approach. 

For data `persistence` we are going to use `Shared Preferences` due to its simplicity and because is a requirement. 

To get an effective decoupling of the views, the **Data Binding** and **View Binding** libraries will be helpful. Therefore, the views will be `single responsibility` compliance and lightweight in the resources usage.

In the UI, we will be using **Material Desing** components mostly. For the list of logs we select the RecyclerView as is more effective than ListView or similars.

## Tasks

- [X] Define the schema for API endpoints.
- [X] Setting basic app structure and resources.
- [X] Create the StopWatch logic using ViewModel and Bindings.
- [ ] Unit tests for stopwatch logic and formatting operations.
- [ ] Espresso tests for basic components.
- [ ] Setup API client and basic CRD operations.
- [ ] Prepare API responses.
- [ ] Make API requests asynchronous.
- [ ] Create the RecyclerView for records.
- [ ] Send the record values to the API and notify to UI.
- [ ] Implement a cache system using SharedPreferences.
- [ ] Add more tests to have a good coverage.

## *Extras*

- [ ] Multiple screen support.
- [ ] Delete records.
- [ ] Dependency Injection with **Hilt**.
- [ ] Animations using **Motion Layout**.

