@file:Suppress("NOTHING_TO_INLINE")

package geo

import geo.internal.LocationInputFieldImpl
import symphony.Fields
import symphony.Label
import symphony.getOrCreate
import kotlin.reflect.KProperty

inline fun LocationInputField(
    name: String,
    isRequired: Boolean = false,
    label: String = name,
    hint: String = label,
    value: GeoLocation? = null,
    isReadonly: Boolean = false,
    noinline validator: ((GeoLocation?) -> Unit)? = null
): LocationInputField = LocationInputFieldImpl(name, isRequired, Label(label, isRequired), hint, value, isReadonly, validator)

inline fun Fields.location(
    name: String,
    isRequired: Boolean = false,
    label: String = name,
    hint: String = label,
    value: GeoLocation? = null,
    isReadonly: Boolean = false,
    noinline validator: ((GeoLocation?) -> Unit)? = null
) = getOrCreate(name) {
    LocationInputField(name, isRequired, label, hint, value, isReadonly, validator)
}

inline fun Fields.location(
    name: KProperty<GeoLocation?>,
    isRequired: Boolean = false,
    label: String = name.name,
    hint: String = label,
    value: GeoLocation? = null,
    isReadonly: Boolean = false,
    noinline validator: ((GeoLocation?) -> Unit)? = null
) = location(name.name, isRequired, label, hint, value, isReadonly, validator)