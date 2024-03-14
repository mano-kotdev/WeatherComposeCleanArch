package com.manoj.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.manoj.weatherapp.R
import com.manoj.weatherapp.data.dataSource.DataWrapper
import com.manoj.weatherapp.domain.model.WeatherResponse
import com.manoj.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherScreen()
                }
            }
        }
    }
}

@Composable
fun WeatherScreen(vm: MainViewModel = hiltViewModel()) {
    val weatherResponse by vm.mWeatherResponse.collectAsState()
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = { vm.getWeather() }) {
            Text("Update Weather")
        }

        Text(
            text = when (weatherResponse) {
                is DataWrapper.Success -> {
                    val response = weatherResponse as DataWrapper.Success<WeatherResponse>
                    stringResource(R.string.weather_info, response.value.name, response.value.weather[0].main)
                }
                is DataWrapper.Failure -> "Body---${(weatherResponse as DataWrapper.Failure).errorBody?.string()} Code---${(weatherResponse as DataWrapper.Failure).errorCode}"
                is DataWrapper.Loading -> "Loading"
                else->{
                    ""
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    Column(modifier = Modifier.fillMaxSize()) {

        Button(onClick = {  }) {
            Text("Update Weather")
        }

        Text(
            text =  "Loading"

        )
    }
}