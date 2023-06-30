package com.example.mystore.ui.profile.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.example.mystore.R
import com.example.mystore.ui.theme.Gray
import com.example.mystore.ui.theme.LightPink20
import com.example.mystore.util.Constants.OFFSET
import com.example.mystore.util.Constants.RADIUS_CAMERA_ICON

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size_large)),
            painter = painterResource(R.drawable.person_crop_circle_fill),
            contentDescription = "profile_image"
        )
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .align(Alignment.BottomEnd)
                .padding(
                    top = dimensionResource(id = R.dimen.padding_small),
                    end = dimensionResource(id = R.dimen.padding_small)
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                tint = Gray,
                contentDescription = null,
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = OFFSET,
                            y = -OFFSET
                        )
                    }
                    .drawBehind {
                        drawCircle(
                            color = LightPink20,
                            radius = RADIUS_CAMERA_ICON
                        )
                    }
            )
        }
    }
}