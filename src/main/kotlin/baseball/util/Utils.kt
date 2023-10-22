package baseball.util

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import kotlin.system.exitProcess

/**
 * 작성자 : 추민수
 * 설명 : 숫자 랜덤 생성, 사용자 입력 등 Util
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
        println("연속된 3자리의 숫자를 입력해주세요:")
        val inputString = Console.readLine()
        try {
            while (inputString == null || !isValidInput(inputString)) {
                throw IllegalArgumentException("유효하지 않은 입력입니다. 시스템이 종료됩니다.")
            }
        }catch (e:IllegalArgumentException){
            println(e.message)
            exitProcess(1)
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


    /**
     * compareResult()
     * 사용자가 입력한 숫자와 컴퓨터가 선택한 숫자를 비교하여 결과를 계산
     * @param userList 사용자가 입력한 숫자 리스트
     * @param computerList 컴퓨터가 선택한 숫자 리스트
     * @return 결과 문자열 (스트라이크, 볼, 낫싱)
     */
    fun compareResult(userList: List<Int>, computerList: List<Int>): String {
        var strikes = 0
        var balls = 0

        for (i in userList.indices) {
            if (userList[i] == computerList[i]) {
                strikes++
            } else if (computerList.contains(userList[i])) {
                balls++
            }
        }

        return when {
            strikes == 3 -> "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료"
            strikes > 0 || balls > 0 -> {
                if (strikes == 0) {
                    "$balls" + "볼"
                } else if (balls == 0) {
                    "$strikes" + "스트라이크"
                }
                "$balls" + "볼 " + "$strikes" + "스트라이크"
            }

            else -> "낫싱"
        }
    }

    /**
     * restartGame()
     * 사용자로부터 게임 재시작 또는 종료를 입력받는 함수
     * @return 게임을 재시작하면 true, 게임을 종료하면 false를 반환
     */
    fun restartGame(): Boolean {
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        var input = Console.readLine()
        while (input != "1" && input != "2") {
            println("유효하지 않은 입력입니다.")
            println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
            input = Console.readLine()
        }
        return when (input) {
            "1" -> true
            "2" -> false
            else -> false
        }
    }
}