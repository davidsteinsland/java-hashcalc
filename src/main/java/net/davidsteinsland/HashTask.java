package net.davidsteinsland;

import javax.swing.SwingWorker;
import javax.swing.JTextField;
import java.util.Map;
import java.util.List;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.*;

public class HashTask extends SwingWorker<Void, HashTask.Result> {

  private Map<String, JTextField> hashes;

  private byte[] plaintext;

  public HashTask(byte[] plaintext, Map<String, JTextField> hashes) {
    this.plaintext = plaintext;
    this.hashes = hashes;
  }

  @Override
  protected void process(List<Result> chunks) {
    for (Result result : chunks) {
      result.field.setText(toHexString(result.result));
    }
  }

  private static String toHexString(byte[] hex) {
    StringBuilder sb = new StringBuilder();
    char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    for (byte b : hex) {
      sb.append(chars[(b >> 4) & 0xF])
        .append(chars[b & 0x0F]);
    }
    return sb.toString();
  }

  @Override
  public Void doInBackground() {
    int completed = 0;
    for (Map.Entry<String, JTextField> entry : hashes.entrySet()) {
      String hashName = entry.getKey();
      JTextField resultField = entry.getValue();

      /* compute hash in current thread or new one? */
      try {
        Digest digest = newInstance(hashName);
        digest.update(plaintext, 0, plaintext.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        publish(new Result(hashName, resultField, result));
      } catch (IllegalStateException e) {
        /* do nothing? */
      }
      setProgress(100 * (++completed/hashes.size()));
    }
    return null;
  }

  @Override
  protected void done() {

  }

  public Digest newInstance(String algorithm) {
    Digest digest;

    if ("GOST3411".equalsIgnoreCase(algorithm)) {
      digest = new GOST3411Digest();
    } else if ("MD2".equalsIgnoreCase(algorithm)) {
      digest = new MD2Digest();
    } else if ("MD4".equalsIgnoreCase(algorithm)) {
      digest = new MD4Digest();
    } else if ("MD5".equalsIgnoreCase(algorithm)) {
      digest = new MD5Digest();
    } else if ("RIPEMD128".equalsIgnoreCase(algorithm)) {
      digest = new RIPEMD128Digest();
    } else if ("RIPEMD160".equalsIgnoreCase(algorithm)) {
      digest = new RIPEMD160Digest();
    } else if ("RIPEMD256".equalsIgnoreCase(algorithm)) {
      digest = new RIPEMD256Digest();
    } else if ("RIPEMD320".equalsIgnoreCase(algorithm)) {
      digest = new RIPEMD320Digest();
    } else if ("SHA1".equalsIgnoreCase(algorithm)) {
      digest = new SHA1Digest();
    } else if ("SHA224".equalsIgnoreCase(algorithm)) {
      digest = new SHA224Digest();
    } else if ("SHA256".equalsIgnoreCase(algorithm)) {
      digest = new SHA256Digest();
    } else if ("SHA384".equalsIgnoreCase(algorithm)) {
      digest = new SHA384Digest();
    } else if ("SHA512".equalsIgnoreCase(algorithm)) {
      digest = new SHA512Digest();
    } else if ("SHA3".equalsIgnoreCase(algorithm)) {
      digest = new SHA3Digest();
    } else if ("Tiger".equalsIgnoreCase(algorithm)) {
      digest = new TigerDigest();
    } else if ("Whirlpool".equalsIgnoreCase(algorithm)) {
      digest = new WhirlpoolDigest();
    } else {
      throw new IllegalStateException("Unsupported digest algorithm " + algorithm);
    }
    return digest;
  }

  public static class Result {

    private String name;

    private JTextField field;

    private byte[] result;

    public Result(String name, JTextField field, byte[] result) {
      this.name = name;
      this.field = field;
      this.result = result;
    }
  }
}
