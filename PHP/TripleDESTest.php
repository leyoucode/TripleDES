<?php
/**
 * Created by PhpStorm.
 * User: liu wei
 * Email: notify.wei@gmail.com
 * Date: 2017/5/19
 * Time: 下午12:09
 */

header("Content-Type: text/html; charset=UTF-8");

class TripleDES
{
    private $iv = "00000000";// ECB 模式被忽略.

    private $encryptKey = "0123456789qwertyuiopasdf";

    //加密
    public function encrypt($encryptStr)
    {

        $encryptKey = $this->encryptKey;

        $module = mcrypt_module_open(MCRYPT_3DES, '', MCRYPT_MODE_ECB, '');

        mcrypt_generic_init($module, $encryptKey, $this->iv);

        //Padding
        $block = mcrypt_get_block_size(MCRYPT_3DES, MCRYPT_MODE_ECB);
        $pad = $block - (strlen($encryptStr) % $block); //Compute how many characters need to pad
        $encryptStr .= str_repeat(chr($pad), $pad); // After pad, the str length must be equal to block or its integer multiples

        //encrypt
        $encrypted = mcrypt_generic($module, $encryptStr);

        //Close
        mcrypt_generic_deinit($module);
        mcrypt_module_close($module);

        return base64_encode($encrypted);

    }

    //解密
    public function decrypt($encryptStr)
    {
        $encryptKey = $this->encryptKey;

        //Open module
        $module = mcrypt_module_open(MCRYPT_3DES, '', MCRYPT_MODE_ECB, '');

        mcrypt_generic_init($module, $encryptKey, $this->iv);

        $encryptedData = base64_decode($encryptStr);
        $encryptedData = mdecrypt_generic($module, $encryptedData);

        return $encryptedData;
    }
}

$encryptString = '测试加密';
$encryptObj = new TripleDES();

$result = $encryptObj->encrypt($encryptString);//加密结果
$decryptString = $decryptString = $encryptObj->decrypt($result);//解密结果
echo $result . "<br/>";
echo $decryptString . "<br/>";
