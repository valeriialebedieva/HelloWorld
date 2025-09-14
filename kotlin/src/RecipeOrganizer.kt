class RecipeOrganizer {

    data class Recipe(
        val name: String,
        val ingredients: List<String>,
        val steps: List<String>,
        val category: String
    )

    private val recipes = mutableListOf<Recipe>()

    fun addRecipe(name: String, ingredients: List<String>, steps: List<String>, category: String) {
        val recipe = Recipe(name, ingredients, steps, category)
        recipes.add(recipe)
        println("✅ Recipe '$name' added under $category.")
    }

    fun showAllRecipes() {
        if (recipes.isEmpty()) {
            println("No recipes available.")
            return
        }
        println("📖 All Recipes:")
        recipes.forEachIndexed { index, recipe ->
            println("${index + 1}. ${recipe.name} [${recipe.category}]")
        }
    }

    fun searchRecipe(keyword: String) {
        val found = recipes.filter { it.name.contains(keyword, ignoreCase = true) }
        if (found.isEmpty()) {
            println("No recipes found for '$keyword'.")
        } else {
            println("🔍 Recipes matching '$keyword':")
            found.forEach { println("- ${it.name} [${it.category}]") }
        }
    }

    fun showByCategory(category: String) {
        val filtered = recipes.filter { it.category.equals(category, ignoreCase = true) }
        if (filtered.isEmpty()) {
            println("No recipes in category '$category'.")
        } else {
            println("🍴 $category Recipes:")
            filtered.forEach { println("- ${it.name}") }
        }
    }

    fun showRecipeDetails(name: String) {
        val recipe = recipes.find { it.name.equals(name, ignoreCase = true) }
        if (recipe == null) {
            println("Recipe not found: $name")
            return
        }
        println("📌 ${recipe.name} (${recipe.category})")
        println("Ingredients:")
        recipe.ingredients.forEach { println("- $it") }
        println("Steps:")
        recipe.steps.forEachIndexed { index, step ->
            println("${index + 1}. $step")
        }
    }

    fun deleteRecipe(name: String) {
        val recipe = recipes.find { it.name.equals(name, ignoreCase = true) }
        if (recipe != null) {
            recipes.remove(recipe)
            println("❌ Recipe '$name' deleted.")
        } else {
            println("Recipe '$name' not found.")
        }
    }

    fun updateCategory(name: String, newCategory: String) {
        val recipe = recipes.find { it.name.equals(name, ignoreCase = true) }
        if (recipe != null) {
            val updated = recipe.copy(category = newCategory)
            recipes[recipes.indexOf(recipe)] = updated
            println("🔄 Recipe '${recipe.name}' moved to '$newCategory'.")
        } else {
            println("Recipe not found.")
        }
    }

    fun countRecipes(): Int {
        println("📊 Total recipes: ${recipes.size}")
        return recipes.size
    }

    fun start() {
        while (true) {
            println("\n--- Recipe Organizer Menu ---")
            println("1. Add Recipe")
            println("2. Show All Recipes")
            println("3. Search Recipe")
            println("4. Show By Category")
            println("5. Show Recipe Details")
            println("6. Delete Recipe")
            println("7. Update Recipe Category")
            println("8. Count Recipes")
            println("9. Exit")
            print("Choose an option: ")

            when (readLine()?.toIntOrNull()) {
                1 -> {
                    print("Enter recipe name: ")
                    val name = readLine() ?: ""
                    print("Enter ingredients (comma separated): ")
                    val ingredients = readLine()?.split(",")?.map { it.trim() } ?: listOf()
                    print("Enter steps (comma separated): ")
                    val steps = readLine()?.split(",")?.map { it.trim() } ?: listOf()
                    print("Enter category: ")
                    val category = readLine() ?: "Other"
                    addRecipe(name, ingredients, steps, category)
                }
                2 -> showAllRecipes()
                3 -> {
                    print("Enter keyword: ")
                    val keyword = readLine() ?: ""
                    searchRecipe(keyword)
                }
                4 -> {
                    print("Enter category: ")
                    val category = readLine() ?: ""
                    showByCategory(category)
                }
                5 -> {
                    print("Enter recipe name: ")
                    val name = readLine() ?: ""
                    showRecipeDetails(name)
                }
                6 -> {
                    print("Enter recipe name to delete: ")
                    val name = readLine() ?: ""
                    deleteRecipe(name)
                }
                7 -> {
                    print("Enter recipe name to update: ")
                    val name = readLine() ?: ""
                    print("Enter new category: ")
                    val newCategory = readLine() ?: ""
                    updateCategory(name, newCategory)
                }
                8 -> countRecipes()
                9 -> {
                    println("👋 Goodbye!")
                    return
                }
                else -> println("Invalid option. Try again.")
            }
        }
    }
}

fun main() {
    val app = RecipeOrganizer()
    app.start()
}
