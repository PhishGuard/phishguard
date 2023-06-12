import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screenshots")
data class Screenshot(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "screenshotName")
    var screenshotName: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "user_id")
    var userId: Int = 0
) {
    constructor() : this(0, "", "", 0)

    constructor(name: String, description: String) : this(0, name, description, 0)

    constructor(id: Int, name: String, description: String) : this(id, name, description, 0)
}
