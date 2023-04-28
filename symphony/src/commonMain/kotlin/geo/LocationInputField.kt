@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package geo

import symphony.properties.Typeable
import symphony.TransformingInputField
import kotlin.js.JsExport

interface LocationInputField : TransformingInputField<String, GeoLocation>, Typeable