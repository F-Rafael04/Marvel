package edu.iesam.marvel.features.superheroes.data.local.xml
import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import edu.iesam.json.R
import edu.iesam.marvel.features.superheroes.domain.SuperHero
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import kotlinx.serialization.json.Json

/**
 * Aqui empieza acceso a datos
 * Conector para gestionar datos en un fichero XML a través de SharedPreferences.
 */
/**
 * Conector para gestionar datos en un fichero XML a través de SharedPreferences.
 *
 */
class SuperHeroXmlLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerializer<List<SuperHero>>
) {

    private val keySuperHero: String = "superhero"

    // Me creo la fuente de datos XML
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero),
        Context.MODE_PRIVATE
    )

    // Metodo guardar
    fun save(superHeroes: List<SuperHero>) {
        sharedPref.edit().apply {
            putString(keySuperHero, serialization.toJson(superHeroes))
            apply()
        }
    }

    public fun findAll(): List<SuperHero> {
        val jsonList: String? = sharedPref.getString(keySuperHero, null)
        jsonList?.let {
            return serialization.fromJson(it)
        }
        return emptyList()
    }

    @SuppressLint("SuspiciousIndentation")
    fun findById(id : String): SuperHero?{

       var lista = findAll()

            lista.forEach {
                if (id == it.id){
                    return it
                }
            }
        return null

    }

    fun deleteById (id: String){

        var lista = findAll()

        lista = lista.filterNot { it.id == id }

        save(lista)

    }

    fun deleteAll(){
        var lista = findAll()

        lista = lista.filter { "1" == it.id }

        lista = lista.filterNot { "1" == it.id }

        save(lista)
    }




}

//    val sharedPref = context.getSharedPreferences(
//        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE)
//
//    private val gson: Gson = Gson()
//
//    public fun findAll() : List<SuperHero>{
//        //sharedPref.getString()
//        return emptyList()
//    }
//
//    fun findById(id: String): SuperHero {
//        val superHeroJson = sharedPref.getString(id, "")
//        return gson.fromJson(superHeroJson, SuperHero::class.java)
//    }
//
//    public fun save(superHeroes: List<SuperHero>) {
//
//        superHeroes.forEach { superHero ->
//            with(sharedPref.edit()) {
//                putString("${superHero.id}", gson.toJson(superHero))
//                apply() //async // commit = sync
//            }
//        }



        //lo de chema esta arriba de aqui


    //cambiando el id en bucle

//        superHeroes.forEach { superHero ->
//            with(sharedPref.edit()) {
//                putString("id-${superHero.id}", superHero.id)
//                putString("name-${superHero.id}", superHero.name)
//                putString("slug-${superHero.id}", superHero.slug)
//                apply()
//            }
//        }

        //metiendolo uno a uno

//        sharedPref.edit().putString("id", superHeroes.first().id)
//        sharedPref.edit().putString("name", superHeroes.first().name)
//        sharedPref.edit().putString("slug", superHeroes.first().slug)


        // metiendolo sin poner todo el rato el .edit
//        with(sharedPref.edit()){
//            putString("id", superHeroes.first().id)
//            putString("name", superHeroes.first().name)
//            putString("slug", superHeroes.first().slug)
//            apply()
//        }
//
//        with(sharedPref.edit()){
//            putString("id1", superHeroes[1].id)
//            putString("name1", superHeroes[1].name)
//            putString("slug1", superHeroes[1].slug)
//            apply()
//        }

        //dos formas de persistir en local
//        sharedPref.edit().apply() // Asincrono
        //sharedPref.edit().commit() //Sincrono


