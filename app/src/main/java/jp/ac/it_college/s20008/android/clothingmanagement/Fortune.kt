package jp.ac.it_college.s20008.android.clothingmanagement

import java.util.*

class Fortune {
    val fortuneList = arrayOf(
        "ホワイト",
        "ブラック",
        "グレー",
        "レッド",
        "ブルー",
        "グリーン",
        "イエロー",
        "オレンジ",
        "ピンク",
        "パープル",
        "ブラウン"
    )

    val descList = arrayOf(
        "#ffffff",
        "#000000",
        "#fffafa",
        "#ff0000",
        "#0000ff",
        "#008000",
        "#ffff00",
        "#ffa500",
        "#ffc0cb",
        "#800080",
        "#a52a2a",
    )

    var name: String = ""
    var description: String = ""

    open fun getFortune(): Fortune {
        val index = Random().nextInt(fortuneList.size)

        this.name = fortuneList[index]
        this.description = descList[index]
        return this

    }

    fun <R> once(block: () -> R): () -> OnceResult<R>? = run {
        var isDone = false;

        // run の返値となるラムダ式
        {
            if (isDone) {
                null
            } else {
                isDone = true

                val result = block()
                OnceResult(result)
            }
        }
    }

    /**
     * [once]で生成した関数が返す、
     * 実行した処理の返値を保持するためのクラス。
     */
    data class OnceResult<R>(val result: R)


}
