
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.example.smartfertigation.data.ResourceRemote
import com.example.smartfertigation.data.UserRepository
import kotlinx.coroutines.launch

@UnstableApi class ProfileViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _userLoggedOut: MutableLiveData<Boolean> = MutableLiveData()
    val userLoggedOut: LiveData<Boolean> = _userLoggedOut
    fun singOut() {
        viewModelScope.launch {
            val result = userRepository.signOut()
            result.let { resourceRemote ->
                when (resourceRemote) {
                    is ResourceRemote.Success -> {
                        if (result.data == true)
                            _userLoggedOut.postValue(true)
                    }

                    is ResourceRemote.Error -> {
                        var msg = result.message
                        when (msg) {
                            "A network error (such as timeout, interrupted connection or unreachable host) has ocurred." -> msg =
                                "Revise su conexión a internet"

                        }
                        _errorMsg.postValue(msg)
                    }

                    else -> {
                        //don´t use
                    }
                }
            }
        }
    }
}
