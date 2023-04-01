package com.example.mobilki.presentation.screens.auth_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.mobilki.domain.models.auth.AuthScreenPages
import com.example.mobilki.presentation.dim.Dimens
import com.example.mobilki.presentation.screens.auth_screen.login.LoginScreen
import com.example.mobilki.presentation.screens.auth_screen.registration.RegistrationScreen
import com.example.mobilki.ui.theme.Purple700
import com.example.mobilki.ui.theme.typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthPagerScreen(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.basePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = Dimens.Paddings.basePadding)
    ) {
        val pagerState = rememberPagerState()
        val pages = remember { AuthScreenPages.values() }

        TabBar(tabs = pages.map { stringResource(id = it.toStringRes()) }, pagerState = pagerState)

        HorizontalPager(
            pageCount = pages.size,
            state = pagerState
        ) {
            when (pages[it]) {
                AuthScreenPages.REGISTRATION -> RegistrationScreen()
                AuthScreenPages.LOGIN -> LoginScreen(navController)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabBar(
    tabs: List<String>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                title = title,
                isSelected = pagerState.currentPage == index,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@Composable
private fun Tab(title: String, isSelected: Boolean, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding),
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .then(modifier)
    ) {
        Text(
            text = title,
            style = typography.h2
        )

        Divider(
            thickness = if (isSelected)
                Dimens.Sizes.boldDividerThickness
            else
                Dimens.Sizes.dividerThickness,
            color = if (isSelected)
                Purple700
            else
                Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
