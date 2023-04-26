import expect.expect
import geo.LatLng
import geo.serializers.LatLngSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class LatLngSerializationTest {

    @Test
    fun should_serialize_a_lat_lng() {
        val cord = LatLng(10.01, -10.01)
        val json = """{"lat":10.01,"lng":-10.01}"""
        expect(Json.encodeToString(LatLngSerializer, cord)).toBe(json)
    }

    @Test
    fun should_deserialize_a_lat_lng() {
        val json = """{"lat":5.01,"lng":0.01}"""
        val cord = LatLng(5.01, 0.01)
        expect(Json.decodeFromString(LatLngSerializer, json)).toBe(cord)
    }
}