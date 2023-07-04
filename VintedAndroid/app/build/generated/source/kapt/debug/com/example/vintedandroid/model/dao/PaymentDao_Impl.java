package com.example.vintedandroid.model.dao;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.example.vintedandroid.model.dto.PaymentDto;
import com.example.vintedandroid.model.dto.enumerated.PaymentMethod;
import com.example.vintedandroid.model.dto.enumerated.Status;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PaymentDao_Impl implements PaymentDao {
  private final RoomDatabase __db;

  public PaymentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<PaymentDto> getAll() {
    final String _sql = "select * from paymentDto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final List<PaymentDto> _result = new ArrayList<PaymentDto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PaymentDto _item;
        final Long _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getLong(_cursorIndexOfId);
        }
        final PaymentMethod _tmpPaymentMethod;
        _tmpPaymentMethod = __PaymentMethod_stringToEnum(_cursor.getString(_cursorIndexOfPaymentMethod));
        final Status _tmpStatus;
        _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        final Long _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        }
        final Long _tmpOrderId;
        if (_cursor.isNull(_cursorIndexOfOrderId)) {
          _tmpOrderId = null;
        } else {
          _tmpOrderId = _cursor.getLong(_cursorIndexOfOrderId);
        }
        _item = new PaymentDto(_tmpId,_tmpPaymentMethod,_tmpStatus,_tmpUserId,_tmpOrderId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public PaymentDto getById(final long paymentId) {
    final String _sql = "select * from paymentDto where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, paymentId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final PaymentDto _result;
      if(_cursor.moveToFirst()) {
        final Long _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getLong(_cursorIndexOfId);
        }
        final PaymentMethod _tmpPaymentMethod;
        _tmpPaymentMethod = __PaymentMethod_stringToEnum(_cursor.getString(_cursorIndexOfPaymentMethod));
        final Status _tmpStatus;
        _tmpStatus = __Status_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
        final Long _tmpUserId;
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _tmpUserId = null;
        } else {
          _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
        }
        final Long _tmpOrderId;
        if (_cursor.isNull(_cursorIndexOfOrderId)) {
          _tmpOrderId = null;
        } else {
          _tmpOrderId = _cursor.getLong(_cursorIndexOfOrderId);
        }
        _result = new PaymentDto(_tmpId,_tmpPaymentMethod,_tmpStatus,_tmpUserId,_tmpOrderId);
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

  private PaymentMethod __PaymentMethod_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "CARD": return PaymentMethod.CARD;
      case "PAYPAL": return PaymentMethod.PAYPAL;
      case "MARK": return PaymentMethod.MARK;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private Status __Status_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "PENDING": return Status.PENDING;
      case "APPROVED": return Status.APPROVED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
