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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
import simplesharing.composeapp.generated.resources.ic_status_battery
import simplesharing.composeapp.generated.resources.ic_status_signal
import simplesharing.composeapp.generated.resources.ic_status_wifi

// (Status bar icons are now coming from Figma-exported resources)

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
private fun StatusBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        // Time display - positioned exactly as in Figma (x=23.18, y=13)
        Text(
            text = "9:41",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.3).sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 23.dp, top = 13.dp)
        )
        
        // Status icons - positioned exactly as in Figma
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 15.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mobile Signal Icon - x=324.21, y=16.67
            Image(
                painter = painterResource(Res.drawable.ic_status_signal),
                contentDescription = "Mobile Signal",
                modifier = Modifier.size(18.dp, 10.dp)
            )

            // WiFi Icon - x=348.53, y=16.33
            Image(
                painter = painterResource(Res.drawable.ic_status_wifi),
                contentDescription = "WiFi",
                modifier = Modifier.size(16.dp, 10.dp)
            )

            // Battery Icon - x=375, y=17
            Image(
                painter = painterResource(Res.drawable.ic_status_battery),
                contentDescription = "Battery",
                modifier = Modifier.size(22.dp, 10.dp)
            )
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        // Following tab - positioned exactly as in Figma (x=132, y=57)
        Text(
            text = "Following",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 132.dp, top = 13.dp)
        )
        
        // For You tab (active) - positioned exactly as in Figma (x=223, y=55)
        Text(
            text = "For You",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.1.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 223.dp, top = 11.dp)
        )
    }
}

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

            // Status Bar - separate layer at top (y=0, height=44)
            StatusBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            )
            
            // Header - separate layer below status bar (y=44, height=44)
            Header(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .offset(y = 44.dp)
            )

            // User Avatar and Plus Button - positioned exactly as in Figma (x=360, y=433)
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(1f)
                    .padding(start = 360.dp, top = 433.dp)
            ) {
                // User Avatar with Plus Button
                Box {
                    // Main user avatar - x=360, y=433, size=47x47
                    Image(
                        painter = painterResource(Res.drawable.user_avatar),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(47.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Red plus button overlay - x=373, y=470.5, size=21x21
                    Box(
                        modifier = Modifier
                            .size(21.dp)
                            .background(Color(0xFFEA4359), CircleShape)
                            .align(Alignment.BottomCenter)
                            .offset(x = 0.dp, y = 1.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_plus),
                            contentDescription = "Add",
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }

            // Likes Section - positioned exactly as in Figma (x=363.5, y=514)
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(1f)
                    .padding(start = 363.5.dp, top = 530.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
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

                    // Heart Icon - x=366, y=514, size=35.5x32.36
                    Box(
                        modifier = Modifier.size(width = 40.dp, height = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.ic_heart),
                            contentDescription = "Like",
                            modifier = Modifier
                                .size(35.dp)
                                .graphicsLayer(scaleX = scale, scaleY = scale)
                        )
                    }

                    // Text "328.7K" - x=363.5, y=552, size=41x16
                    Text(
                        text = "328.7K",
                        color = Color.White,
                        fontSize = 6.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(41.dp)
                    )
                }
            }

            // Bottom Share Section - positioned with more space for Likes frame
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(bottom = 60.dp)
            ) {
                // Share to Section - exact positioning from Figma
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(226.dp)
                        .background(
                            Color(0xFFF5F5F4),
                            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
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

                    // Social Apps Row - exact positioning from Figma
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // WhatsApp - positioned at x:20, y:629
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(Color(0xFF65D072), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_whatsapp),
                                    contentDescription = "WhatsApp",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "WhatsApp",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // WhatsApp Status - positioned at x:89, y:629
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(Color(0xFF65D072), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_whatsapp),
                                    contentDescription = "WhatsApp Status",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "WhatsApp\nstatus",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Message - positioned at x:158, y:628
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(Color(0xFFEA4359), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_message),
                                    contentDescription = "Message",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Message",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // SMS - positioned at x:227, y:628
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(Color(0xFF5FC87D), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_sms),
                                    contentDescription = "SMS",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "SMS",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Messenger - positioned at x:294, y:629
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(Color(0xFF2D67F6), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_messenger),
                                    contentDescription = "Messenger",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Messenger",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Instagram - positioned at x:365, y:628
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(49.dp)
                                    .background(
                                        Brush.radialGradient(
                                            colors = listOf(
                                                Color(0xFFFFDD55),
                                                Color(0xFFFF543E),
                                                Color(0xFFC837AB)
                                            )
                                        ),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.ic_instagram),
                                    contentDescription = "Instagram",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Instagram",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Separator - positioned at x:16, y:713
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color(0xFFDADBDB),
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Actions Row - exact positioning from Figma
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 21.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Report - positioned at x:21, y:727
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
                                    painter = painterResource(Res.drawable.ic_report),
                                    contentDescription = "Report",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Report",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Not interested - positioned at x:89, y:727
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
                                    painter = painterResource(Res.drawable.ic_not_interested),
                                    contentDescription = "Not interested",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Not\ninterested",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Save video - positioned at x:155.5, y:727
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
                                    painter = painterResource(Res.drawable.ic_save_video),
                                    contentDescription = "Save video",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Save video",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Duet - positioned at x:228, y:727
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
                                    painter = painterResource(Res.drawable.ic_duet),
                                    contentDescription = "Duet",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Duet",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // React - positioned at x:297, y:727
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
                                    painter = painterResource(Res.drawable.ic_react),
                                    contentDescription = "React",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "React",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }

                        // Add to Favorites - positioned at x:366, y:727
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
                                    painter = painterResource(Res.drawable.ic_favorites),
                                    contentDescription = "Add to Favorites",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = "Add to\nFavorites",
                                fontSize = 11.sp,
                                color = Color(0xFF4E4F57),
                                textAlign = TextAlign.Center,
                                lineHeight = 13.4.sp
                            )
                        }
                    }
                }

                // Cancel Button - positioned at x:0, y:813
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
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF161722)
                    )
                }
            }

            // Home Indicator - overlay at the very bottom (y=861, h=35)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(35.dp)
                    .zIndex(2f),
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