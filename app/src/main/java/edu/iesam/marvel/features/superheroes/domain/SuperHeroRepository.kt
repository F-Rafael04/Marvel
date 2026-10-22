package edu.iesam.marvel.features.superheroes.domain

interface SuperHeroRepository {

    fun getAll(): List<SuperHero>
}