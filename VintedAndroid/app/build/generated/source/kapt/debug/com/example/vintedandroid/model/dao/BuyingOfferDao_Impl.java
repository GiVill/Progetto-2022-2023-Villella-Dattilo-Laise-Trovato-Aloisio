package com.example.vintedandroid.model.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.vintedandroid.model.dto.BuyingOfferDto;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLEngineResult;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BuyingOfferDao_Impl implements BuyingOfferDao {
  private final RoomDatabase __db;

  public BuyingOfferDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<BuyingOfferDto> getAll() {
    final String _sql = "select * from buyingOfferDto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfInsertionId = CursorUtil.getColumnIndexOrThrow(_cursor, "insertionId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final List<BuyingOfferDto> _result = new ArrayList<BuyingOfferDto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final BuyingOfferDto _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final float _tmpPrice;
        _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
        final SSLEngineResult.Status _tmpStatus;
        _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        final long _tmpInsertionId;
        _tmpInsertionId = _cursor.getLong(_cursorIndexOfInsertionId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _item = new BuyingOfferDto(_tmpId,_tmpPrice,_tmpStatus,_tmpInsertionId,_tmpUserId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public BuyingOfferDto getById(final long buyingId) {
    final String _sql = "select * from buyingOfferDto where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, buyingId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfInsertionId = CursorUtil.getColumnIndexOrThrow(_cursor, "insertionId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final BuyingOfferDto _result;
      if(_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final float _tmpPrice;
        _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
        final SSLEngineResult.Status _tmpStatus;
        _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        final long _tmpInsertionId;
        _tmpInsertionId = _cursor.getLong(_cursorIndexOfInsertionId);
        final long _tmpUserId;
        _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        _result = new BuyingOfferDto(_tmpId,_tmpPrice,_tmpStatus,_tmpInsertionId,_tmpUserId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private SSLEngineResult.Status __Status_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "BUFFER_UNDERFLOW": return SSLEngineResult.Status.BUFFER_UNDERFLOW;
      case "BUFFER_OVERFLOW": return SSLEngineResult.Status.BUFFER_OVERFLOW;
      case "OK": return SSLEngineResult.Status.OK;
      case "CLOSED": return SSLEngineResult.Status.CLOSED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
