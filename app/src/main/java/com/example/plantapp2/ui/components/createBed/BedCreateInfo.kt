package com.example.plantapp2.ui.components.createBed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantapp2.ui.theme.CenteredBed


@Composable
fun BedCreationInfo(viewModel: BedCreationViewModel) {
    val bedLength by viewModel.bedLength.collectAsState()
    val bedWidth by viewModel.bedWidth.collectAsState()
    val bedName by viewModel.bedName.collectAsState()
    val lengthInput by viewModel.lengthInput.collectAsState()
    val widthInput by viewModel.widthInput.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(
                    text = "Create your virtual bed here. Type the length and width of your bed in cm, below.",
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        value = lengthInput,
                        onValueChange = { viewModel.updateLengthInput(it) },
                        placeholder = { Text(text = "Length in cm") },
                        modifier = Modifier
                            .padding(16.dp).weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = lengthInput.toIntOrNull() == null
                    )
                    TextField(
                        value = widthInput,
                        onValueChange = { viewModel.updateWidthInput(it) },
                        placeholder = { Text(text = "Width in cm") },
                        modifier = Modifier
                            .padding(16.dp).weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = widthInput.toIntOrNull() == null
                    )
                }
                Text(
                    text = "Length: $bedLength cm, Width: $bedWidth cm",
                    color = if (lengthInput.toIntOrNull() != null && widthInput.toIntOrNull() != null) Color.Black else Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    CenteredBed(length = bedLength, width = bedWidth)

                    TextField(
                        value = bedName,
                        onValueChange = { viewModel.updateBedName(it) },
                        placeholder = { Text("Enter bed name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    Button(
                        onClick = { viewModel.saveBed() },
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color.Green),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text("Save", color = Color.Black)
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun BedCreationInfoPreview() {
    BedCreationInfo(viewModel = BedCreationViewModel())
}

