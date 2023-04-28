package geo.internal

import geo.GeoLocation
import geo.LocationInputField
import symphony.Label
import symphony.internal.TransformedDataField
import symphony.internal.utils.DataTransformer
import symphony.internal.utils.Typer
import symphony.internal.validators.CompoundValidator
import symphony.internal.validators.LambdaValidator
import symphony.internal.validators.RequirementValidator

@PublishedApi
internal class LocationInputFieldImpl(
    override val name: String,
    override val isRequired: Boolean,
    override val label: Label,
    override val hint: String,
    private val value: GeoLocation?,
    override val isReadonly: Boolean,
    private val validator: ((GeoLocation?) -> Unit)?
) : TransformedDataField<String, GeoLocation>(value), LocationInputField {
    override val serializer = GeoLocation.serializer()
    override val transformer = DataTransformer<String, GeoLocation>({ it.toString() }, { googleParser.parseOrNull(it) })

    override val cv = CompoundValidator(
        data, feedback,
        RequirementValidator(data, feedback, label.capitalizedWithoutAstrix(), isRequired),
        LambdaValidator(data, feedback, validator)
    )

    override fun type(text: String) = Typer(data.value.input, setter).type(text)

    private companion object {
        val googleParser: GooglePlacesApiParser = GooglePlacesApiParser()
    }
}