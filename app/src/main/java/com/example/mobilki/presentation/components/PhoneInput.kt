package com.example.mobilki.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mobilki.R
import com.example.mobilki.presentation.dim.Dimens

@Composable
fun PhoneInput(
    phoneCodeState: MutableState<String>,
    phoneNumberState: MutableState<String>
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (codeEt, numberEt) = createRefs()

        TextField(
            value = phoneCodeState.value,
            onValueChange = { phoneCodeState.value = it },
            placeholder = { Text(text = stringResource(R.string.plus_seven)) },
            modifier = Modifier
                .constrainAs(codeEt) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .width(Dimens.Sizes.countryCodeFieldWidth)
        )

        TextField(
            value = phoneNumberState.value,
            onValueChange = { phoneNumberState.value = it },
            placeholder = { Text(text = stringResource(R.string.phone_placeholder)) },
            modifier = Modifier
                .constrainAs(numberEt) {
                    top.linkTo(parent.top)
                    start.linkTo(codeEt.end, margin = Dimens.Paddings.halfPadding)
                    end.linkTo(parent.end)

                    width = Dimension.fillToConstraints
                }
        )
    }
}
