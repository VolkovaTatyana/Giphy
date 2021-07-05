package tatyana.volkova.app.giphy.data.mapper

interface IMapper<T,E>{
    fun mapFrom(from: T): E
}