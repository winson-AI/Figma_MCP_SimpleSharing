package com.example.simplesharing

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import simplesharing.composeapp.generated.resources.Res
import simplesharing.composeapp.generated.resources.ic_heart
import simplesharing.composeapp.generated.resources.ic_instagram
import simplesharing.composeapp.generated.resources.ic_message
import simplesharing.composeapp.generated.resources.ic_messenger
import simplesharing.composeapp.generated.resources.ic_plus
import simplesharing.composeapp.generated.resources.ic_report
import simplesharing.composeapp.generated.resources.ic_save_video
import simplesharing.composeapp.generated.resources.ic_sms
import simplesharing.composeapp.generated.resources.user_avatar
import simplesharing.composeapp.generated.resources.background_image
import simplesharing.composeapp.generated.resources.ic_whatsapp
import simplesharing.composeapp.generated.resources.ic_not_interested
import simplesharing.composeapp.generated.resources.ic_duet
import simplesharing.composeapp.generated.resources.ic_react
import simplesharing.composeapp.generated.resources.ic_favorites

data class SocialApp(
    val name: String,
    val icon: org.jetbrains.compose.resources.DrawableResource,
    val backgroundColor: Color
)

data class ActionItem(
    val name: String,
    val icon: org.jetbrains.compose.resources.DrawableResource
)

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC4C4C4))
        ) {
            // Background image
            Image(
                painter = painterResource(Res.drawable.background_image),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            // Dark overlay for better text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )
            
            // Status bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp, vertical = 13.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "9:41",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = (-0.3).sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Signal strength
                    Text(
                        text = "●●●●",
                        color = Color.White,
                        fontSize = 8.sp
                    )
                    // WiFi symbol
                    Text(
                        text = "📶",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    // Battery
                    Text(
                        text = "🔋",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
            
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 57.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Following",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(end = 32.dp)
                )
                Text(
                    text = "For You",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.1.sp
                )
            }
            
            // Right side controls
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 13.dp, top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // User avatar with plus button
                Box {
                    Image(
                        painter = painterResource(Res.drawable.user_avatar),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(47.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(Res.drawable.ic_plus),
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(21.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = 10.dp)
                    )
                }
                
                // Heart and likes
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val infiniteTransition = rememberInfiniteTransition()
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_heart),
                            contentDescription = "Like",
                            modifier = Modifier
                                .size(32.dp)
                                .graphicsLayer(scaleX = scale, scaleY = scale)
                        )
                    }
                    Text(
                        text = "328.7K",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            // Bottom sheet
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                // Share to section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(226.dp)
                        .background(
                            Color(0xFFF5F5F4),
                            RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(top = 10.dp, bottom = 20.dp)
                ) {
                    Text(
                        text = "Share to",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF161722),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    // Social media icons
                    val socialApps = listOf(
                        SocialApp("WhatsApp", Res.drawable.ic_whatsapp, Color(0xFF65D072)),
                        SocialApp("WhatsApp\nstatus", Res.drawable.ic_whatsapp, Color(0xFF65D072)),
                        SocialApp("Message", Res.drawable.ic_message, Color(0xFFEA4359)),
                        SocialApp("SMS", Res.drawable.ic_sms, Color(0xFF5FC87D)),
                        SocialApp("Messenger", Res.drawable.ic_messenger, Color(0xFF2D67F6)),
                        SocialApp("Instagram", Res.drawable.ic_instagram, Color(0xFFC837AB))
                    )
                    
                    LazyRow(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(socialApps) { app ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(app.icon),
                                    contentDescription = app.name,
                                    modifier = Modifier.size(if (app.name.contains("Message")) 49.dp else 47.dp)
                                )
                                Text(
                                    text = app.name,
                                    fontSize = 11.sp,
                                    color = Color(0xFF4E4F57),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 13.4.sp
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    // Separator
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color(0xFFDADBDB),
                        thickness = 1.dp
                    )
                    
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    // Action buttons
                    val actions = listOf(
                        ActionItem("Report", Res.drawable.ic_report),
                        ActionItem("Not\ninterested", Res.drawable.ic_not_interested),
                        ActionItem("Save video", Res.drawable.ic_save_video),
                        ActionItem("Duet", Res.drawable.ic_duet),
                        ActionItem("React", Res.drawable.ic_react),
                        ActionItem("Add to\nFavorites", Res.drawable.ic_favorites)
                    )
                    
                    LazyRow(
                        modifier = Modifier.padding(horizontal = 21.dp),
                        horizontalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        items(actions) { action ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(47.dp)
                                        .background(Color(0xFFE8E8E7), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(action.icon),
                                        contentDescription = action.name,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Text(
                                    text = action.name,
                                    fontSize = 11.sp,
                                    color = Color(0xFF4E4F57),
                                    textAlign = TextAlign.Center,
                                    lineHeight = 13.4.sp
                                )
                            }
                        }
                    }
                }
                
                // Bottom bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(83.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        color = Color(0xFF161722)
                    )
                }
                
                // Home indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(134.dp)
                            .height(5.dp)
                            .background(Color(0xFF060606), RoundedCornerShape(100.dp))
                    )
                }
            }
        }
    }
}