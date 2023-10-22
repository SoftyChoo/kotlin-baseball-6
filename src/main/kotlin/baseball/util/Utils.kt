package baseball.util
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

/**
 * 작성자 : 추민수
 * 설명 : 숫자 랜덤 생성, 사용자 입력 등
 */
object Utils {

    /**
     * compareList()
     * 컴퓨터는 1에서 9까지 랜덤으로 서로 다른 임의의 수 3개를 할당 받은 후 리스트의 형태로 return
     */
    fun getRandomList(): List<Int> {

        val computerList = mutableListOf<Int>()

        while (computerList.size < 3) {
            val randomNumber = Randoms.pickNumberInRange(1, 9)

            if (!computerList.contains(randomNumber)) {
                computerList.add(randomNumber)
            }
        }
        println("컴퓨터가 3개의 숫자를 할당 받았습니다.")
        return computerList
    }

    /**
     * inputNum()
     * 사용자가 입력한 연속된 3자리의 문자열을 받아 한글자씩 나눠 리스트의 형태로 return
     */
    fun inputNum(): List<Int> {
        println("연속된 3자리의 숫자를 입력하세요:")

        var inputString = Console.readLine()
        while (inputString == null || !isValidInput(inputString)) {
            println("유효하지 않은 입력입니다. 연속된 3자리의 숫자를 입력하세요:")
            inputString = Console.readLine()
        }
        // 문자열을 한글자씩 나눠 리스트에 나눠 전송
        return inputString.chunked(1).map { it.toInt() }
    }

    /**
     * isValidInput()
     * 유효한 입력(길이:3, 입력형태:Int)인지 확인 후 Boolean return
     */
    fun isValidInput(input: String): Boolean {
        return input.length == 3 && input.all { it.isDigit() }
    }
}