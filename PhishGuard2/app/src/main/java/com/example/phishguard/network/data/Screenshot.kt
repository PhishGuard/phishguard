import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screenshots")
data class Screenshot(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val screenshotName: String,
    val imageBitmap: ImageBitmap
)