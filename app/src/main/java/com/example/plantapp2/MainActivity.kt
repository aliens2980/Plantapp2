package com.example.plantapp2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantapp2.ui.theme.Plantapp2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Plantapp2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    GreetingImage(
                        messenger = stringResource(R.string.hello_world),
                        from = stringResource(R.string.plantapp),
                        modifier = Modifier.padding(8.dp)

                        )
                }

            }
        }
    }
}


@Composable
fun GreetingText(messenger: String,from: String ,modifier: Modifier= Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        
         ) {
        Text(
            text = messenger,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
            
        )
    }

}
@Composable
fun GreetingImage(messenger: String,from: String, modifier: Modifier= Modifier) {
    val image= painterResource(R.drawable.horde_background)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F,
            modifier = Modifier.fillMaxSize()

        )

        GreetingText(
            messenger = messenger,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}
@Preview(
    showBackground = true
            )
@Composable
fun GreetingPreview() {
    Plantapp2Theme {
        GreetingImage(
            messenger = stringResource(R.string.hello_world),
            from = stringResource(R.string.plantapp)
        )
    }
}
// Do: This function is a descriptive PascalCased noun as a visual UI element
@Composable
fun FancyButton(text: String) {}


// Do: This function is a descriptive PascalCased noun as a non-visual element
// with presence in the composition
@Composable
fun BackButtonHandler() {}


// Don't: This function is a noun but is not PascalCased!
@Composable
fun fancyButton(text: String) {}


// Don't: This function is PascalCased but is not a noun!
@Composable
fun RenderFancyButton(text: String) {}


