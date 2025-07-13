## 📰 Newspresso – Kotlin News App

**Newspresso** is a sleek and modern news application built using **Kotlin** and **Android Studio**, designed to fetch and display the latest headlines from [NewsAPI.org](https://newsapi.org/). With a minimalist UI and smooth navigation, users can browse, read, and search for news articles from around the world — all in one place.

---

### 🚀 Features

* 🔥 Fetches top headlines from NewsAPI.org
* 📒 Displays news articles in a dynamic list using RecyclerView
* 📄 Full article detail view with image, description, and publication time
* 🔍 In-app search to filter articles by keyword
* ☕ Splash screen with animated startup
* ⚡ Clean architecture with MVVM pattern
* 🖼️ Glide for image loading and caching
* 🌐 Retrofit for network communication
* 🧠 ViewModel & LiveData for reactive UI updates

---

### 🛠️ Tech Stack

* **Language:** Kotlin
* **IDE:** Android Studio
* **Libraries:**

  * Retrofit + Gson – API & JSON parsing
  * Glide – Image loading
  * RecyclerView – Efficient list display
  * ViewModel + LiveData – Lifecycle-aware data handling
  * Coroutine – Async operations

---

### 📦 Project Structure

```
com.example.newspresso
🗂️ api            // Retrofit API interface
🗂️ model          // Data models for API response
🗂️ repository     // Data fetching logic
🗂️ ui             // Activities, Adapters, and layouts
🗂️ viewmodel      // ViewModel class
🗂️ util           // Constants and helpers
🗂️ res/layout     // All UI XML files
```

---

### 📸 Screenshots

> *(Include screenshots of MainActivity, ArticleDetail, and Splash here)*

---

### 📌 Setup

1. Clone this repo
2. Add your **NewsAPI key** in `Constants.kt`
3. Build and run the app on an emulator or device

---

### 📄 License

This project is open-source and available under the [MIT License](LICENSE).
